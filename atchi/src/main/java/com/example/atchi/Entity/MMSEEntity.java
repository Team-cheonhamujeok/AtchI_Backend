package com.example.atchi.Entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@Entity(name = "MMSE")
public class MMSEEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int mid;
//    @CollectionTable(
//            name = "MMSEQuestionList",
//            joinColumns = @JoinColumn(name = "MMSE_id",referencedColumnName = "id")
//    )
    @ElementCollection
    @OrderColumn
    private List<String> questions  = new ArrayList<String>();
    private Date date;
    private int reulst;
}
