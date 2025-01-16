package ido.style.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {
    private Integer no;
    private String fileName;
//    @ToString.Exclude
    private byte[] data;
}







