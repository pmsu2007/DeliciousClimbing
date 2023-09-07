package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sangmyungdae.deliciousclimbing.domain.enums.Region;
import sangmyungdae.deliciousclimbing.dto.famousMountain.*;
import sangmyungdae.deliciousclimbing.service.FamousMountainService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mountains")
public class FamousMountainController {
    private final FamousMountainService service;

    @GetMapping(value = "")
    public String mountainListMainPage(@RequestParam(required = false) String region,
                                   @RequestParam(required = false) String keyword,
                                   @PageableDefault(size = 100, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
                                   Model model) {
        FamousMountainSearchDto dto = FamousMountainSearchDto.builder()
                .keyword(keyword)
                .region(region == null ? null : Region.valueOf(region))
                .build();
        List<FamousMountainList> famousMountainList = service.getMountainList(dto, pageable);
        model.addAttribute("famousMountainList", famousMountainList);

        return "mountainInfo";
    }

    @GetMapping(value = "/iframe")
    public String mountainListPage() {


        return "mountainInfoPhoto";
    }

    @GetMapping(value = "/{mountainId}")
    public String mountainDetailPage(@PathVariable Long mountainId, Model model) {
        //TODO: Session 통해 로그인중인 userId 갖고와서 삽입
        Long userId = null;
//        FamousMountain famousMountain = service.getMountainDetail(mountainId, userId);
//        model.addAttribute("famousMountain", famousMountain);

        //임시 코드
        FamousMountain famousMountain = service.getMountainDetail(mountainId, 33L);
        model.addAttribute("famousMountain", famousMountain);

        return "mountainInfoDetail";
    }

    @GetMapping(value = "/recommend")
    public String recommendMainPage() {
        return "template-name";
    }

    @GetMapping(value = "/recommend/{page}")
    public String recommendPage(@PathVariable String page) {
        return "template-name";
    }

    @GetMapping(value = "/recommend/result")
    public String recommendResultPage() {
        return null;
    }

    @PostMapping(value = "/likes/{mountainId}")
    public String likeMountain(@PathVariable Long mountainId) {

        //TODO: Session 통해 로그인중인 userId 갖고와서 삽입
        Long userId = null;
        service.insertLike(mountainId, userId);

        return "redirect:/mountains/{mountainId}";
    }

    @PostMapping(value = "/unlikes/{mountainId}")
    public String unlikeMountain(@PathVariable Long mountainId) {

        //TODO: Session 통해 로그인중인 userId 갖고와서 삽입
        Long userId = null;
        service.deleteLike(mountainId, userId);

        return "redirect:/mountains/{mountainId}";
    }

    @PostMapping(value = "/recommend")
    public String createRecommend(RecommendDto dto) {
        return null;
    }
}
