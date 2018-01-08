package pl.edu.amu.wmi.erykandroidcommon.recycler.editdelete

import pl.edu.amu.wmi.erykandroidcommon.recycler.delete.DeleteRecyclerFragment

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 28.07.17.
 */
abstract class EditDeleteRecyclerFragment<T, S : EditDeleteItemViewHolder<T>> : DeleteRecyclerFragment<T, S>() {

    public override fun initAdapter() {
        super.initAdapter()
        //        ((EditDeleteViewAdapter<T, S>) adapter).getEditClicks().subscribe(this::edit);
    }

    protected abstract fun edit(item: T)
}
