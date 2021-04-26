package com.project.commerz.web.controller;

import com.project.commerz.model.Ad;
import com.project.commerz.model.Category;
import com.project.commerz.model.Location;
import com.project.commerz.service.AdService;
import com.project.commerz.service.CategoryService;
import com.project.commerz.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//@RestController
@Controller
@RequestMapping("/all_ads")
public class listAdsController {
    private final int NUM_ADS_IN_PAGE = 5;
    private final AdService adService;
    private final CategoryService categoryService;
    private final LocationService locationService;

    public listAdsController(AdService adService, CategoryService categoryService, LocationService locationService) {
        this.adService = adService;
        this.categoryService = categoryService;
        this.locationService = locationService;
    }

    @GetMapping("/{page}")
    public String getMainPage(@RequestParam(required = false) String error,
                              @RequestParam(name = "full_text", required = false) String full_text,
                              @RequestParam(name = "cat_id", required = false) String cat_id,
                              @RequestParam(name = "loc_id", required = false) String loc_id,
                              @PathVariable Integer page,
                              Model model,
                              HttpServletRequest req) {
        // page 1, ads with index 0 - 9
        // page 2, ads wih index 10 - 19
        // page 3, ads wih index 20 - 29
        Category c = categoryService.findById(cat_id);
        Location l = locationService.findById(loc_id);

        List<Ad> adListStream = adService.searchAds(full_text, c, l);
        long total_num = adListStream.size();

        if (total_num % NUM_ADS_IN_PAGE > 0) {
            total_num = Math.floorDiv(total_num, NUM_ADS_IN_PAGE) + 1;
        } else
            total_num = Math.floorDiv(total_num, NUM_ADS_IN_PAGE);
        List<Integer> pages = new LinkedList<>();
        for (int i = 1; i <= total_num; i++) {
            pages.add(i);
        }


        List<Ad> adList = adListStream.stream()
                .limit(NUM_ADS_IN_PAGE * page)
                .skip((page - 1) * NUM_ADS_IN_PAGE)
                .collect(Collectors.toList());

        List<Category> categoryList = categoryService.findAllCategories();
        List<Location> locationList = locationService.findAllLocations();
        model.addAttribute("allAds", adList);
        req.getSession().setAttribute("allAds", adList);
        req.getSession().setAttribute("categories", categoryList);
        req.getSession().setAttribute("locations", locationList);
        req.getSession().setAttribute("page_number", page);
        req.getSession().setAttribute("total_number_of_pages_list", pages);
        return "listAds";
    }

    @PostMapping("/filter_ads")
    public String filter(HttpServletRequest req) {
        String fullTextSearch = req.getParameter("full_text_search");
        String categoryId = req.getParameter("category_droplist");    // category id
        String locationId = req.getParameter("location_droplist");    // location id
        String returnString = "redirect:/all_ads/1?cat_id=" + categoryId + "&loc_id=" + locationId;
        if (!fullTextSearch.isEmpty()) {
            returnString += "&full_text=" + fullTextSearch;
        }
        return returnString;
    }

    @GetMapping("/ad/{adId}")
    public String adDetails(@PathVariable Long adId,
                            Model model,
                            HttpServletRequest req) {

        Ad ad = adService.findAdById(adId).get();
        req.getSession().setAttribute("name", ad.getAdName());
        req.getSession().setAttribute("description", ad.getAdDescription());
        req.getSession().setAttribute("category", ad.getCategory().getCategoryName());
        req.getSession().setAttribute("email", ad.geteMail());
        req.getSession().setAttribute("phone", ad.getPhoneNumber());
        req.getSession().setAttribute("price", ad.getPrice());
        req.getSession().setAttribute("location", ad.getLocation().getLocation());
        return "adDetails";
    }

    @PostMapping("/createNewAd")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String createNewAd(HttpServletRequest req) {
        String name = req.getParameter("newAdName");
        String description = req.getParameter("newAdDescription");
        Long categoryId = Long.valueOf(req.getParameter("newAdCategory"));
        Long locationId = Long.valueOf(req.getParameter("newAdLocation"));
        Long price = Long.valueOf(req.getParameter("newAdPrice"));
        String phone = req.getParameter("newAdPhone");
        String email = req.getParameter("newAdEmail");
        System.out.println(name + description + categoryId + locationId + price + phone + email);

        adService.save(name, description, categoryId, locationId, price, phone, email);
        return "redirect:/all_ads/1";
    }

    @GetMapping("/newAdPage")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String newAdPage() {
        return "createAd";
    }

    @GetMapping("/getLoginPage")
    public String getLoginPage() {
        return "redirect:/login";
    }

    @GetMapping("/getLogoutPage")
    public String getLogoutPage() {
        return "redirect:/logout";
    }
}
