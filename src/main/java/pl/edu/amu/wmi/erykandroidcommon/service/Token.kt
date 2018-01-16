package pl.edu.amu.wmi.erykandroidcommon.service

import pl.edu.amu.wmi.erykandroidcommon.user.UserInterface

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@softra.pl> on 18.10.17.
 */
interface Token : UserInterface {
    val token: String
}
