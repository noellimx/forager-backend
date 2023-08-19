package com.noellimx.main.controllers.rest.reference;


import com.noellimx.main.controllers.rest.reference.bodytype.request.YoutubeReferenceCreateRequestBody;
import com.noellimx.main.controllers.rest.reference.bodytype.response.YoutubeReferenceResponse;
import com.noellimx.main.controllers.rest.utils.JsonResponse;
import com.noellimx.main.entity.YoutubeReference;
import com.noellimx.main.service.app.YoutubeReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reference/youtube")
public class YoutubeReferenceController {

  final YoutubeReferenceService youtubeReferenceService;

  @Autowired
  public YoutubeReferenceController(YoutubeReferenceService youtubeReferenceService) {
    this.youtubeReferenceService = youtubeReferenceService;
  }

  @PostMapping("/")
  public ResponseEntity<YoutubeReferenceResponse> create(
      @RequestBody YoutubeReferenceCreateRequestBody form,
      Authentication a) {

    UserDetails user = (UserDetails) a.getPrincipal();

    YoutubeReference ref = youtubeReferenceService.create(form.videoId, form.sfaLicenseNo,
        form.timestamp, user.getUsername());

    System.out.println("[tt]");
    System.out.println(ref);

    YoutubeReferenceResponse tt = YoutubeReferenceResponse.fromEntity(ref);

    return ResponseEntity.status(200).body(tt);
  }

  @GetMapping("/all")
  public JsonResponse getAll() {
    return new JsonResponse<>(
        youtubeReferenceService.getAll(), "");
  }
}
