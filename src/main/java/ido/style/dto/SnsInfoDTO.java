package ido.style.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SnsInfoDTO {
    private String id;
    private String userId;
    private String ci;
    private String name;
    private LocalDateTime connectedDate;
}
