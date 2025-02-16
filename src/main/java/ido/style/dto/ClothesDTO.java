package ido.style.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class ClothesDTO {
    private Integer no;
    private String name;
    private String detail;
    private LocalDateTime uploadedAt;
    private Integer category;
    private List<ClothesImageDTO> images;

    private UserDTO user;

}











