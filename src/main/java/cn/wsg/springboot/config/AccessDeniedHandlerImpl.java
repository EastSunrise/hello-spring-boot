package cn.wsg.springboot.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * Handles {@link AccessDeniedException}s.
 *
 * @author Kingen
 */
@Component("accessDeniedHandler")
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
        throws IOException {
        response.sendError(HttpStatus.FORBIDDEN.value());
    }
}
