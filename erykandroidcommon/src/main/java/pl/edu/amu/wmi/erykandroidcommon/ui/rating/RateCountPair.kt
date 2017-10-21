package pl.edu.amu.wmi.erykandroidcommon.ui.rating;


import lombok.Data;

@Data
public class RateCountPair {

    private Integer id;

    private int count;

    private float rating;

    private float your;

    private VoteType voteType;


}
