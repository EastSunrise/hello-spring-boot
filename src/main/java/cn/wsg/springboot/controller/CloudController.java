package cn.wsg.springboot.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kingen
 */
@RestController
@RequestMapping("/cloud/api")
public class CloudController {

    @PostMapping(value = "/root", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> root(@RequestParam Map<String, String> params, HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        // todo check params
        String api = params.get("api");
        if (StringUtils.isBlank(api)) {
            return ResponseEntity.badRequest().build();
        }
        if ("test".equals(api)) {
            request.getRequestDispatcher("/cloud/api/test").forward(request, response);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok("This is test of " + params.get("name"));
    }
}
