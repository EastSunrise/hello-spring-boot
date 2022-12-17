package cn.wsg.springboot.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cloud/api")
public class CloudController {

    @PostMapping(value = "/root", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> root(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // todo check params
        Map<String, String[]> params = request.getParameterMap();
        String[] api = params.get("api");
        if (api == null || api.length != 1) {
            return ResponseEntity.badRequest().build();
        }
        if ("test".equals(api[0])) {
            request.getRequestDispatcher("/cloud/api/test").forward(request, response);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("This is test of api");
    }
}
