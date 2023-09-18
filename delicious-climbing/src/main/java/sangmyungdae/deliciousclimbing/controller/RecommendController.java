package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sangmyungdae.deliciousclimbing.domain.entity.TbFamousMountain;
import sangmyungdae.deliciousclimbing.dto.recommend.RecommendMountain;
import sangmyungdae.deliciousclimbing.service.RecommendService;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping("/main")
    public String recommendMainPage(Model model){
        // 랜덤으로 entity 하나 추출해서 넣기
        // 살짝의 로직을 추가할 필요가 있어보임 안되면 말고
        // 난이도 별표시 해결 -> js로 해야할 듯
        // 주소처리는 나중에 pull 받아서 하기
        List<RecommendMountain> randMountainList = recommendService.findMountainRand();

        model.addAttribute("randMountainList",randMountainList);

        return "recommend";
    }
}
