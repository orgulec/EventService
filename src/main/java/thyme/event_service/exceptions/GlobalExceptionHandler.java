package thyme.event_service.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException ex){
        ModelAndView mav = new ModelAndView();
        ex.printStackTrace();
        mav.addObject("errorMessage", ex.getMessage());
        mav.setViewName("notFound");
        return mav;
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView handleNoSuchElementException(NoSuchElementException ex){
        ModelAndView mav = new ModelAndView();
        ex.printStackTrace();
        mav.addObject("errorMessage", ex.getMessage());
        mav.setViewName("notFound");
        return mav;
    }


}
