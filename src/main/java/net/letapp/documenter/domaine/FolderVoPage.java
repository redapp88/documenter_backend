package net.letapp.documenter.domaine;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderVoPage {
private List<FolderVo> content;
private int pageSize;
private long numberOfElements;
}
