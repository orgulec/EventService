package thyme.event_service.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.security.sasl.AuthenticationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException ex){
        ModelAndView mav = new ModelAndView();
        ex.printStackTrace();
        mav.addObject("errorMessage", ex.getMessage());
        mav.setViewName("/errors/notFound");
        return mav;
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView handleNoSuchElementException(NoSuchElementException ex){
        ModelAndView mav = new ModelAndView();
        ex.printStackTrace();
        mav.addObject("errorMessage", ex.getMessage());
        mav.setViewName("/errors/notFound");
        return mav;
    }
    @ExceptionHandler(WrongInputDtoException.class)
    public ModelAndView handleWrongInputDtoException(WrongInputDtoException ex){
        ModelAndView mav = new ModelAndView();
        ex.printStackTrace();
        mav.addObject("errorMessage", ex.getMessage());
        mav.setViewName("/errors/badRequest");
        return mav;
    }
    @ExceptionHandler(AuthenticationException.class)
    public ModelAndView handleAuthenticationException (AuthenticationException  ex){
        ModelAndView mav = new ModelAndView();
        ex.printStackTrace();
        mav.addObject("errorMessage", ex.getMessage());
        mav.setViewName("/errors/badRequest");
        return mav;
    }
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ModelAndView handleInternalAuthenticationServiceException (InternalAuthenticationServiceException  ex){
        ModelAndView mav = new ModelAndView();
        ex.printStackTrace();
        mav.addObject("errorMessage", ex.getMessage());
        mav.setViewName("/errors/badRequest");
        return mav;
    }


}
