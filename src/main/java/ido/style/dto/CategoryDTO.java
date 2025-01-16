package ido.style.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CategoryDTO {
    private Integer no;
    private String name;
    private List<CategoryDTO> children;
    private Integer level;
}
