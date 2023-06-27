package miniproject.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {
    private String warehouseSrc;
    private String warehouseDst;
    private String storeDst;
    private String goods;
    private Long quantity;
}

