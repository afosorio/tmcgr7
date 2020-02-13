package com.grupo7.moneychange.data.repository

import com.grupo7.moneychange.utils.PermissionChecker


interface CountryRepository {
        suspend fun getCountryLocation(permissionChecker: PermissionChecker): String
}
