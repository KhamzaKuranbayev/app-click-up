package uz.pdp.appclickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickup.entity.template.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskHistory extends AbstractEntity {

    @ManyToOne
    private Task task;

    @Column(nullable = false)
    private String changeFieldName;

    @Column(nullable = false)
    private String before;

    @Column(nullable = false)
    private String after;

    @Column(nullable = false)
    private String data;


}
