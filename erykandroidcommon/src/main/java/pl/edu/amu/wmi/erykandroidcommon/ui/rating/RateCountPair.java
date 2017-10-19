package pl.edu.amu.wmi.erykandroidcommon.ui.rating;


import org.greenrobot.greendao.annotation.Id;

import lombok.Data;
import lombok.Getter;

public class RateCountPair {

    @Id
    private Integer id;

    @Getter
    private int count;

    @Getter
    private float rating;

    private float your;

    private VoteType voteType;

    public enum VoteType {
        GYM, TRAINER
    }

}
