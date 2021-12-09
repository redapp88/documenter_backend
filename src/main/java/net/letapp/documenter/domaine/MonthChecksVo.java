package net.letapp.documenter.domaine;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonthChecksVo {
private String year;
private String month;
private Long checks;
}
