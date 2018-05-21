package pl.edu.amu.wmi.erykandroidcommon.recycler.editdelete

import pl.edu.amu.wmi.erykandroidcommon.recycler.delete.DeleteRecyclerFragment

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class EditDeleteRecyclerFragment<T : Any, S : EditDeleteItemViewHolder<T>> : DeleteRecyclerFragment<T, S>() {

    override fun initBaseAdapter() {
        super.initBaseAdapter()
        //        ((EditDeleteViewAdapter<T, S>) adapter).getEditClicks().subscribe(this::edit);
    }

    protected abstract fun edit(item: T)
}
