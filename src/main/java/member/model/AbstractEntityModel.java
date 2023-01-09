package member.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AbstractEntityModel implements Serializable {
    @CreatedDate
    @Column(name = "reg_date", updatable = false)
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(name = "update_date", updatable = true)
    private LocalDateTime lastModifieDateTime;

    @Column(name="created_by_user", nullable=false)
    @CreatedBy
    private String createdByUser;

    @Column(name = "modified_by_user", nullable = false)
    @LastModifiedBy
    private String modifiedByUser;

}
