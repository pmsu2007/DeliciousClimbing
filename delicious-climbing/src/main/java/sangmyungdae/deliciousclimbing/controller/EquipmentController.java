package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangmyungdae.deliciousclimbing.domain.entity.TbEquipment;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;
import sangmyungdae.deliciousclimbing.dto.*;
import sangmyungdae.deliciousclimbing.dto.address.Address;
import sangmyungdae.deliciousclimbing.service.EquipmentService;
import sangmyungdae.deliciousclimbing.util.FileStore;

import java.net.MalformedURLException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final FileStore fileStore;


    @GetMapping("/list")
    public String equipmentListPage(@RequestParam(required = false) EquipmentType type,
                                    @RequestParam(required = false) Long addressCode,
//                                    @RequestParam(required = false) String latest,
                                    Model model) {
        EquipmentSearchDto dto = EquipmentSearchDto.builder()
                .equipmentType(type)
                .adressCode(addressCode)
//                .latest(latest)
                .build();
        List<Equipment> equipments = equipmentService.getPostList(dto);
        model.addAttribute("type",type);
        model.addAttribute("addressCode",addressCode);
        model.addAttribute("equipments", equipments);
//        model.addAttribute("latest",latest);
        return "equipMain";
    }


    @GetMapping("/detail/{postId}")
    public String equipmentDetailPage(@PathVariable Long postId,
                                      Model model) {
        Equipment equipment = equipmentService.getPostDetail(postId);
        model.addAttribute("equipment",equipment);
        return "equipDetail";
    }
    // /equipment/create
    @GetMapping("/create")
    public String equipmentCreatePage(EquipmentDto equipmentDto,
                                      Model model) {
        model.addAttribute("equipmentDto",equipmentDto);
        return "equipCreate";
    }

    @GetMapping("/update/{postId}")
    public String equipmentUpdatePage(@PathVariable Long postId, Model model) {
        Equipment equipment =equipmentService.getPostDetail(postId);
        model.addAttribute(equipment);

        model.addAttribute(equipment);

        return "equipUpdate";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute EquipmentDto dto,
                             MultipartFile[] files) {
//        for (MultipartFile file :
//            files) {
//            System.out.println("file = "+file.toString());
//        }
        System.out.println("1차 확인!!!!!!!타이틀!!!!! = " + dto.getTitle());
        System.out.println("dto = " + dto.getContent());
        System.out.println("dto.getAddressCode() = " + dto.getAddressCode());
        System.out.println("dto.getType() = " + dto.getType());
//        if (files != null) {
//            for (MultipartFile file : files) {
//                System.out.println("file = " + file.toString()); // 일단 파일은 계속 널값 처리 됨
//            }
//        }
        // user_id DTO에 추가
        Equipment equipment = equipmentService.createPost(dto, files);
        return "redirect:/equipment/detail/"  + equipment.getId();
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@ModelAttribute EquipmentDto dto,
                             @PathVariable Long postId,
                             RedirectAttributes redirectAttributes) {
        // 사용자 auth 확인
        // user_id DTO에 추가
        equipmentService.updatePost(postId, dto);
        return "redirect:/equipment/{type}/{postId}";
    }

    @PostMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId,
                             RedirectAttributes redirectAttributes) {
        return "redirect:/equipment/{type}";
    }


    @ResponseBody
    @GetMapping("/files/{filename}")
    public Resource getFile(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:"+fileStore.getFullPath(filename));
    }

    @PostMapping("/tradeStatus/{postId}")
    public String updateTradeStatus(@PathVariable Long postId){

        equipmentService.updateTradeStatus(postId);

        return "redirect:/equipment/detail/"+postId;

    }



}
