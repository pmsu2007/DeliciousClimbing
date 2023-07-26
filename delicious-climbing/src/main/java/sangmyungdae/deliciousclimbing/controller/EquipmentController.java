package sangmyungdae.deliciousclimbing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sangmyungdae.deliciousclimbing.domain.enums.EquipmentType;
import sangmyungdae.deliciousclimbing.dto.*;
import sangmyungdae.deliciousclimbing.dto.address.Address;
import sangmyungdae.deliciousclimbing.service.EquipmentService;
import sangmyungdae.deliciousclimbing.util.FileStore;

import java.io.File;
import java.net.MalformedURLException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final FileStore fileStore;

    @GetMapping("/{type}")
    public String equipmentListPage(@PathVariable EquipmentType type,
                                    @RequestParam(required = false)Address address,
                                    @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                    Model model) {
        EquipmentSearchDto dto = EquipmentSearchDto.builder()
                .equipmentType(type)
                .adressCode(address.getCode())
                .build();

        Page<Equipment> equipments = equipmentService.getPostList(dto, pageable);
        model.addAttribute("type",type);
        model.addAttribute("equipments", equipments);
        return "equipMain_Thyme";
    }


    @GetMapping("/{type}/{postId}")
    public String equipmentDetailPage(@PathVariable Long postId,
                                      Model model) {
        Equipment equipment = equipmentService.getPostDetail(postId);
        model.addAttribute("equipment",equipment);
        return "equipDetail_Thyme";
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
                             MultipartFile[]  files) {
        for (MultipartFile file :
            files) {
            System.out.println("file = "+file.toString());
        }
        // user_id DTO에 추가
        Equipment equipmentPost = equipmentService.createPost(dto, files);
        return "redirect:/equipments/"+equipmentPost.getType()+"/"+equipmentPost.getId();
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@ModelAttribute EquipmentDto dto,
                             @PathVariable Long postId,
                             RedirectAttributes redirectAttributes) {
        // 사용자 auth 확인
        // user_id DTO에 추가
        equipmentService.updatePost(postId, dto);
        return "redirect:/equipments/{type}/{postId}";
    }

    @PostMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId,
                             RedirectAttributes redirectAttributes) {
        return "redirect:/equipments/{type}";
    }

    @ResponseBody
    @GetMapping("/files/{filename}")
    public Resource getFile(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file"+fileStore.getFullPath(filename));
    }

}
