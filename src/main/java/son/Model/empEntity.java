package son.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by ngocson on 30/09/2017.
 */
@Entity
@Table(name = "Employee")
public class empEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empID")
    private Integer ID;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    @NotNull(message = "Invalid Date")
    private Date date;
    @Column(name = "image")
    private String image;

    @Transient
    private CommonsMultipartFile multipartFile;

    public CommonsMultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(CommonsMultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public String toString() {
        return "empEntity{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", image='" + image + '\'' +
                '}';
    }

    public empEntity() {
    }

    public empEntity(String name, Date date, String image) {
        this.name = name;
        this.date = date;
        this.image = image;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
