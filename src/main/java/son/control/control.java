package son.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import son.Model.empEntity;
import son.Service.empService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ngocson on 30/09/2017.
 */
@Controller
public class control {
    @Autowired
    private empService empService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String emp(Model model, @RequestParam(value = "ID", required = false) Integer ID) {

        empEntity empEntity = null;

        if (ID != null) {
            empEntity = empService.tim(ID);
        } else {
            empEntity = new empEntity();
        }
        model.addAttribute("emp", empEntity);
        model.addAttribute("list", empService.LIST());
        return "employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String saveEmp(@ModelAttribute("emp") empEntity empEntity) throws IOException {
        if (empEntity.getMultipartFile().getSize() > 0) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File("/home/ngocson/Templates/" + empEntity.getMultipartFile().getOriginalFilename())));
            empEntity.setImage(empEntity.getMultipartFile().getOriginalFilename());
            outputStream.write(empEntity.getMultipartFile().getBytes());
            outputStream.close();
        }else {
            System.out.println("AAAAAA"+empEntity.getID());
            son.Model.empEntity empEntity1 = empService.tim(empEntity.getID());
            empEntity.setImage(empEntity1.getImage());
        }
        empService.save(empEntity);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/delete/{ID}", method = RequestMethod.GET)
    public String xoa(@PathVariable("ID") Integer ID) {
        empEntity empEntity = empService.tim(ID);
        empService.remove(empEntity);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/image/{ID}")
    public void image(@PathVariable("ID") Integer ID, HttpServletResponse httpServletResponse) throws IOException {
        empEntity empEntity = null;
        if (ID != null) {
            empEntity = empService.tim(ID);
        }
        if (empEntity != null && empEntity.getImage() != null) {
            httpServletResponse.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            File file = new File("/home/ngocson/Templates/" + empEntity.getImage());
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, httpServletResponse.getOutputStream());
            httpServletResponse.flushBuffer();
        }
    }
}
