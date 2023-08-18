package com.noellimx.main.controllers.rest.reference;


import com.noellimx.main.controllers.rest.reference.bodytype.request.YoutubeReferenceCreateRequestBody;
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

  final YoutubeReferenceService service;

  @Autowired
  public YoutubeReferenceController(YoutubeReferenceService service) {
    this.service = service;
  }

  @PostMapping("/")
  public ResponseEntity<YoutubeReference> create(
      @RequestBody YoutubeReferenceCreateRequestBody form,
      Authentication a) {

    UserDetails user = (UserDetails) a.getPrincipal();

    YoutubeReference ref = service.create(form.videoId, form.sfaLicenseNo,
        form.timestamp, user.getUsername());

    return ResponseEntity.status(200).body(ref);
  }

  @GetMapping("/all")
  public JsonResponse getAll() {
    return new JsonResponse<>(
        service.getAll(), "");
  }
}
