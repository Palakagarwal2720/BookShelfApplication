package com.example.bookshelfapplication.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.bookshelfapplication.navigation.Routes
import com.example.bookshelfapplication.ui.dataClass.Country
import com.example.bookshelfapplication.ui.dataClass.CountryData
import com.example.bookshelfapplication.ui.util.DataStoreUtil
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController){
    val context = LocalContext.current
    val countryResponse = "{\"status\":\"OK\",\"status-code\":200,\"version\":\"1.0\",\"access\":\"public\",\"data\":{\"DZ\":{\"country\":\"Algeria\",\"region\":\"Africa\"},\"AO\":{\"country\":\"Angola\",\"region\":\"Africa\"},\"BJ\":{\"country\":\"Benin\",\"region\":\"Africa\"},\"BW\":{\"country\":\"Botswana\",\"region\":\"Africa\"},\"BF\":{\"country\":\"BurkinaFaso\",\"region\":\"Africa\"},\"BI\":{\"country\":\"Burundi\",\"region\":\"Africa\"},\"CV\":{\"country\":\"CaboVerde\",\"region\":\"Africa\"},\"CM\":{\"country\":\"Cameroon\",\"region\":\"Africa\"},\"CF\":{\"country\":\"CentralAfricanRepublic(the)\",\"region\":\"Africa\"},\"TD\":{\"country\":\"Chad\",\"region\":\"Africa\"},\"KM\":{\"country\":\"Comoros(the)\",\"region\":\"Africa\"},\"CD\":{\"country\":\"Congo(theDemocraticRepublicofthe)\",\"region\":\"Africa\"},\"CG\":{\"country\":\"Congo(the)\",\"region\":\"Africa\"},\"CI\":{\"country\":\"Côted'Ivoire\",\"region\":\"Africa\"},\"DJ\":{\"country\":\"Djibouti\",\"region\":\"Africa\"},\"EG\":{\"country\":\"Egypt\",\"region\":\"Africa\"},\"GQ\":{\"country\":\"EquatorialGuinea\",\"region\":\"Africa\"},\"ER\":{\"country\":\"Eritrea\",\"region\":\"Africa\"},\"SZ\":{\"country\":\"Eswatini\",\"region\":\"Africa\"},\"ET\":{\"country\":\"Ethiopia\",\"region\":\"Africa\"},\"GA\":{\"country\":\"Gabon\",\"region\":\"Africa\"},\"GM\":{\"country\":\"Gambia(the)\",\"region\":\"Africa\"},\"GH\":{\"country\":\"Ghana\",\"region\":\"Africa\"},\"GN\":{\"country\":\"Guinea\",\"region\":\"Africa\"},\"GW\":{\"country\":\"Guinea-Bissau\",\"region\":\"Africa\"},\"KE\":{\"country\":\"Kenya\",\"region\":\"Africa\"},\"LS\":{\"country\":\"Lesotho\",\"region\":\"Africa\"},\"LR\":{\"country\":\"Liberia\",\"region\":\"Africa\"},\"LY\":{\"country\":\"Libya\",\"region\":\"Africa\"},\"MG\":{\"country\":\"Madagascar\",\"region\":\"Africa\"},\"MW\":{\"country\":\"Malawi\",\"region\":\"Africa\"},\"ML\":{\"country\":\"Mali\",\"region\":\"Africa\"},\"MR\":{\"country\":\"Mauritania\",\"region\":\"Africa\"},\"MU\":{\"country\":\"Mauritius\",\"region\":\"Africa\"},\"YT\":{\"country\":\"Mayotte\",\"region\":\"Africa\"},\"MA\":{\"country\":\"Morocco\",\"region\":\"Africa\"},\"MZ\":{\"country\":\"Mozambique\",\"region\":\"Africa\"},\"NA\":{\"country\":\"Namibia\",\"region\":\"Africa\"},\"NE\":{\"country\":\"Niger(the)\",\"region\":\"Africa\"},\"NG\":{\"country\":\"Nigeria\",\"region\":\"Africa\"},\"RE\":{\"country\":\"Réunion\",\"region\":\"Africa\"},\"RW\":{\"country\":\"Rwanda\",\"region\":\"Africa\"},\"SH\":{\"country\":\"SaintHelena,AscensionandTristandaCunha\",\"region\":\"Africa\"},\"ST\":{\"country\":\"SaoTomeandPrincipe\",\"region\":\"Africa\"},\"SN\":{\"country\":\"Senegal\",\"region\":\"Africa\"},\"SC\":{\"country\":\"Seychelles\",\"region\":\"Africa\"},\"SL\":{\"country\":\"SierraLeone\",\"region\":\"Africa\"},\"SO\":{\"country\":\"Somalia\",\"region\":\"Africa\"},\"ZA\":{\"country\":\"SouthAfrica\",\"region\":\"Africa\"},\"SS\":{\"country\":\"SouthSudan\",\"region\":\"Africa\"},\"SD\":{\"country\":\"Sudan(the)\",\"region\":\"Africa\"},\"TZ\":{\"country\":\"Tanzania,theUnitedRepublicof\",\"region\":\"Africa\"},\"TG\":{\"country\":\"Togo\",\"region\":\"Africa\"},\"TN\":{\"country\":\"Tunisia\",\"region\":\"Africa\"},\"UG\":{\"country\":\"Uganda\",\"region\":\"Africa\"},\"EH\":{\"country\":\"WesternSahara*\",\"region\":\"Africa\"},\"ZM\":{\"country\":\"Zambia\",\"region\":\"Africa\"},\"ZW\":{\"country\":\"Zimbabwe\",\"region\":\"Africa\"},\"AQ\":{\"country\":\"Antarctica\",\"region\":\"Antarctic\"},\"BV\":{\"country\":\"BouvetIsland\",\"region\":\"Antarctic\"},\"TF\":{\"country\":\"FrenchSouthernTerritories(the)\",\"region\":\"Antarctic\"},\"HM\":{\"country\":\"HeardIslandandMcDonaldIslands\",\"region\":\"Antarctic\"},\"GS\":{\"country\":\"SouthGeorgiaandtheSouthSandwichIslands\",\"region\":\"Antarctic\"},\"AF\":{\"country\":\"Afghanistan\",\"region\":\"Asia\"},\"AM\":{\"country\":\"Armenia\",\"region\":\"Asia\"},\"AZ\":{\"country\":\"Azerbaijan\",\"region\":\"Asia\"},\"BD\":{\"country\":\"Bangladesh\",\"region\":\"Asia\"},\"BT\":{\"country\":\"Bhutan\",\"region\":\"Asia\"},\"IO\":{\"country\":\"BritishIndianOceanTerritory(the)\",\"region\":\"Asia\"},\"BN\":{\"country\":\"BruneiDarussalam\",\"region\":\"Asia\"},\"KH\":{\"country\":\"Cambodia\",\"region\":\"Asia\"},\"CN\":{\"country\":\"China\",\"region\":\"Asia\"},\"GE\":{\"country\":\"Georgia\",\"region\":\"Asia\"},\"HK\":{\"country\":\"HongKong\",\"region\":\"Asia\"},\"IN\":{\"country\":\"India\",\"region\":\"Asia\"},\"ID\":{\"country\":\"Indonesia\",\"region\":\"Asia\"},\"JP\":{\"country\":\"Japan\",\"region\":\"Asia\"},\"KZ\":{\"country\":\"Kazakhstan\",\"region\":\"Asia\"},\"KP\":{\"country\":\"Korea(theDemocraticPeople'sRepublicof)\",\"region\":\"Asia\"},\"KR\":{\"country\":\"Korea(theRepublicof)\",\"region\":\"Asia\"},\"KG\":{\"country\":\"Kyrgyzstan\",\"region\":\"Asia\"},\"LA\":{\"country\":\"LaoPeople'sDemocraticRepublic(the)\",\"region\":\"Asia\"},\"MO\":{\"country\":\"Macao\",\"region\":\"Asia\"},\"MY\":{\"country\":\"Malaysia\",\"region\":\"Asia\"},\"MV\":{\"country\":\"Maldives\",\"region\":\"Asia\"},\"MN\":{\"country\":\"Mongolia\",\"region\":\"Asia\"},\"MM\":{\"country\":\"Myanmar\",\"region\":\"Asia\"},\"NP\":{\"country\":\"Nepal\",\"region\":\"Asia\"},\"PK\":{\"country\":\"Pakistan\",\"region\":\"Asia\"},\"PH\":{\"country\":\"Philippines(the)\",\"region\":\"Asia\"},\"SG\":{\"country\":\"Singapore\",\"region\":\"Asia\"},\"LK\":{\"country\":\"SriLanka\",\"region\":\"Asia\"},\"TW\":{\"country\":\"Taiwan(ProvinceofChina)\",\"region\":\"Asia\"},\"TJ\":{\"country\":\"Tajikistan\",\"region\":\"Asia\"},\"TH\":{\"country\":\"Thailand\",\"region\":\"Asia\"},\"TL\":{\"country\":\"Timor-Leste\",\"region\":\"Asia\"},\"TM\":{\"country\":\"Turkmenistan\",\"region\":\"Asia\"},\"UZ\":{\"country\":\"Uzbekistan\",\"region\":\"Asia\"},\"VN\":{\"country\":\"VietNam\",\"region\":\"Asia\"},\"BZ\":{\"country\":\"Belize\",\"region\":\"CentralAmerica\"}}}"
    val options = remember { mutableStateListOf<String>()}
    var selectedCountry by remember { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        val countryData = Gson().fromJson(countryResponse, CountryData::class.java)
        val keys = countryData.data?.keySet() ?: arrayListOf()
        keys.forEach { key ->
            val country = Gson().fromJson(countryData.data?.get(key), Country::class.java)
            options.add(country.country ?: "")
        }
    }
    var name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var isFocused by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    Column(Modifier.background(Color.White), verticalArrangement = Arrangement.SpaceBetween)
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(border = BorderStroke(1.dp, Color.Gray)),
                value = name,
                onValueChange = {
                    name = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    focusedLabelColor = Color.Blue
                ),
                placeholder = {
                    Text(text = "Name..")
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(border = BorderStroke(1.dp, Color.Gray)),
                value = password,
                onValueChange = {
                    isFocused = true
                    password = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Black,
                    focusedLabelColor = Color.Blue
                ),
                placeholder = {
                    Text(text = "Password..")
                },
                isError = !isValidPassword(password)
            )
            if(!isValidPassword(password) && isFocused) {
                Text(text = "Please Enter Valid Password", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(20.dp))
            if(options.isNotEmpty()) {
                DropDown(options = options.toList(), onValueChange = {
                    selectedCountry = it
                })
            }
        }
        Button(
            onClick = {
                scope.launch {
                    DataStoreUtil(context).saveBoolean(DataStoreUtil.Keys.USER_LOGIN, true)
                    DataStoreUtil(context).saveBoolean(DataStoreUtil.Keys.USER_REGISTER, true)
                    navController.navigate(Routes.BOOK_SHELF_SCREEN)
                }
                      }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            enabled = name.isNotEmpty() && selectedCountry.isNotEmpty() && isValidPassword(password)
        ) {
            Text("Submit")
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(options:List<String>,onValueChange:(String)->Unit )
{
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown
    OutlinedTextField(
        value = selectedOptionText,
        onValueChange = { selectedOptionText = it },
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                //This value is used to assign to the DropDown the same width
                textfieldSize = coordinates.size.toSize()
            },
        label = { Text("Country..") },
        trailingIcon = {
            Icon(icon, "contentDescription",
                Modifier.clickable { expanded = !expanded })
        }
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = !expanded },
        modifier = Modifier.width(with(LocalDensity.current) { textfieldSize.width.toDp() })
    ) {
        options.forEach { label ->
            DropdownMenuItem(onClick = {
                selectedOptionText = label
                onValueChange(label)
                expanded = false
            },
                text = {
                    Text(text = label)
                })
        }
    }
}
fun isValidPassword(password: String): Boolean {
    val regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
    val pattern: Pattern = Pattern.compile(regex)
    val matcher: Matcher = pattern.matcher(password)
    return matcher.matches()
}