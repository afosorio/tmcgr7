package com.grupo7.moneychange.repository

import com.grupo7.moneychange.utils.PermissionChecker


interface ICountryRepository {
        suspend fun getCountryLocation(permissionChecker: PermissionChecker): String
}
