package com.example.assignment1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment1.dataman.DataRecordsManager
import com.example.assignment1.ui.theme.Assignment1Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserRegistrationForm()
                }
            }
        }
    }
}

@Composable
fun UserRegistrationForm(){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = DataRecordsManager(context)

    val savedUsernameState = dataStore.getUsername.collectAsState(initial = "")
    val savedEmailAddressState = dataStore.getEmailAddress.collectAsState(initial = "")
    val savedIDState = dataStore.getID.collectAsState(initial = "")

    var username by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Registration Form",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 20.dp)
                .fillMaxWidth(),
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                .fillMaxWidth(),
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username", color = Color.Gray, fontSize = 12.sp) },
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                .fillMaxWidth(),
            value = emailAddress,
            onValueChange = { emailAddress = it },
            label = { Text(text = "Email Address", color = Color.Gray, fontSize = 12.sp) },
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                .fillMaxWidth(),
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "ID", color = Color.Gray, fontSize = 12.sp) },
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)) {

            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(end = 10.dp)
                    .background(
                        color = Color(230, 219, 129, 255),
                        shape = RoundedCornerShape(corner = CornerSize(20))
                    )
                    .border(
                        width = 2.dp,
                        color = Color(255, 235, 59, 255),
                        shape = RoundedCornerShape(corner = CornerSize(20))
                    ),
                onClick = {
                    //launch the class in a coroutine scope
                    scope.launch {
                        if (savedUsernameState.value.toString().isNotEmpty()&&savedEmailAddressState.value.toString().isNotEmpty()&&savedIDState.value.toString().isNotEmpty()){
                            username = savedUsernameState.value.toString()
                            emailAddress = savedEmailAddressState.value.toString()
                            id = savedIDState.value.toString()
                        }
                        else{
                            Toast.makeText(context,"No data saved to load!!",Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(230, 219, 129, 255))
            )
            {
                // button text
                Text(
                    text = "Load",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(horizontal = 10.dp)
                    .background(
                        color = Color(144, 206, 144, 255),
                        shape = RoundedCornerShape(corner = CornerSize(20))
                    )
                    .border(
                        width = 2.dp,
                        color = Color(76, 175, 80, 255),
                        shape = RoundedCornerShape(corner = CornerSize(20))
                    ),
                onClick = {
                    //launch the class in a coroutine scope
                    scope.launch {
                        dataStore.saveUserData(username, emailAddress, id)
                        username = ""
                        emailAddress = ""
                        id = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(144, 206, 144, 255))
            )
            {
                // button text
                Text(
                    text = "Save",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(start = 10.dp)
                    .background(
                        color = Color(238, 140, 140, 255),
                        shape = RoundedCornerShape(corner = CornerSize(20))
                    )
                    .border(
                        width = 2.dp,
                        color = Color(244, 67, 54, 255),
                        shape = RoundedCornerShape(corner = CornerSize(20))
                    ),
                onClick = {
                    //launch the class in a coroutine scope
                    scope.launch {
                        dataStore.clearUserData()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(238, 140, 140, 255))
            )
            {
                // button text
                Text(
                    text = "Clear",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        val savedUsername = savedUsernameState.value ?: ""
        val savedEmailAddress = savedEmailAddressState.value ?: ""
        val savedID = savedIDState.value ?: ""

        if(savedUsername.isEmpty()&&savedEmailAddress.isEmpty()&&savedID.isEmpty()){
            Text(
                text = "No data is saved yet?!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(all = 50.dp)
                    .fillMaxWidth()
            )

        }
        else{
            Column(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)) {
                Text(
                    text = savedUsername,
                    color = Color.Black,
                    fontSize = 18.sp
                )
                Text(
                    text = savedEmailAddress,
                    color = Color.Black,
                    fontSize = 18.sp
                )
                Text(
                    text = savedID,
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UserRegistrationForm()
}