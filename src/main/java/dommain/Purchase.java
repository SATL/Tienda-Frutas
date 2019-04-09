package dommain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Purchase {
    String productName;
    Integer quantity;
    Product product;
}
