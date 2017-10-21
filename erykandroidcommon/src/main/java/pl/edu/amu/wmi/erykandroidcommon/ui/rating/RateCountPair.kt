package pl.edu.amu.wmi.erykandroidcommon.ui.rating


import lombok.Data

@Data
class RateCountPair {

    private val id: Int? = null

    private val count: Int = 0

    private val rating: Float = 0.toFloat()

    private val your: Float = 0.toFloat()

    private val voteType: VoteType? = null


}
