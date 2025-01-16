package ido.style.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class StyleCategoryDTO {
    private Integer no;
    private String name;
    private List<StyleCategoryDTO> children;
    private Integer level;
}
