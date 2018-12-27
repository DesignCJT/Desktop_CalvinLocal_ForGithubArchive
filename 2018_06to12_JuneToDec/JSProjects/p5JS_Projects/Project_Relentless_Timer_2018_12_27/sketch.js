//Project Relentless sketch
//Made using info from Coding Train p5.js series on Youtube
//Overall project is Collaboration between Andrew Jung, Calvin Tomaschko
//and PM'd by Will Jung
//created 11/3/18


//Varible table creation, these are global vars used throughout the code

//_____INPUTS

var taskName; //These are for text fields
var startTimeInputH;
var startTimeInputM;
var startTimeInputS;

var startTimeDisplay;

var durTimeInput; //Dur.ation time 
var reasonInput;
//var endTimeInput; // Eventually this will be used if (filled) {end is not duration end}
var doneQIntInput;

var nameStor; //Storing the text the moment submit is pressed
var startTimeStorH;
var startTimeStorM;
var startTimeStorS;

var durTimeStor;
var reasonStor;
//var endTimeStor;
var doneQIntStor;

var durInMiliSeconds; //gets rid of miliseconds
var timeIsUp;

var cirSize = 0; //ellipse is used to signal in case js.text didn't

var fillTextSubmit;
var startTimeDisplay;
var fillTextStartAlarm;
var fillTextDoneQ;

var doneQCounter=0;
var doneQOptionsFill;


function setup() {
	// First we make a canvas, to see results without console log
	// Button DOM element and the mousepress on it
	// when mouse pressed over button "submitting" func is called
	// text fields are referenced
	createCanvas(400, 300);
	createP("Project Relentless In Ptag JS");
	taskInput();
}


function submitting() {
	//storing the text field reference
	//adjTime handles all the milisecond conversion
	//setTimeout calls a function by name at a time amount

	nameStor = taskName.value(); //___TASK

	reasonStor = reasonInput.value(); //___REASON(S) FOR TASK

	doneQIntStor = doneQIntInput.value(); //___DONE? INTERVALS

	//Math out startTime versus current time //___ START TIME, INPUTS FOR

	startTimeStorH = int(startTimeInputH.value());
	startTimeStorM = int(startTimeInputM.value());
	startTimeStorS = int(startTimeInputS.value());

	startTimeDisplay = startTimeStorH + ":" + startTimeStorM + ":" + startTimeStorS;

	var curTimeH = int(hour());
	var curTimeM = int(minute());
	var curTimeS = int(second());


	// print(curTimeH + " " + curTimeM + " " + curTimeS);
	// print(startTimeStorH + " " + startTimeStorM + " " + startTimeStorS);

	var startTimeInSec = (startTimeStorH * 3600) + (startTimeStorM * 60) + (startTimeStorS);

	var curTimeInSec = (curTimeH * 3600) + (curTimeM * 60) + (curTimeS);

	var timeToStartAlarmInMili = (startTimeInSec - curTimeInSec) * 1000;

	if (timeToStartAlarmInMili < 0) {
		timeToStartAlarmInMili = (((24 * 60 * 60) + startTimeInSec) - curTimeInSec) * 1000;
	} //If alarm is for next day
	// print (startTimeDisplay);
	print(startTimeInSec);
	print(curTimeInSec);
	print(timeToStartAlarmInMili);

	durTimeStor = durTimeInput.value(); //___DURATION
	minTimeStor = durTimeStor * 60 //turning min input into seconds

	durInMiliSeconds = 1000 * minTimeStor; //Goes to setTimeout for fillSplashDoneQ

	var timeGrabbed = timeGrab();

	//endTimeStor = endTimeInput.value();eventual use
	fillSubmittal(timeGrabbed); //To be used as an argument passed into 

	//One setTimeout for StartAlarm
	setTimeout(fillSplashStartAlarm, timeToStartAlarmInMili);
}

function fillSubmittal(timeGiven) { //Takes in an argument of time for display
	fillTextSubmit = "it was " + timeGiven + " at submit for " + nameStor;
	print(fillTextSubmit);
}

function fillSplashStartAlarm() {
	var timeGrabbed = timeGrab();
	fillTextStartAlarm = "TimeToStart, " + nameStor + " at " + timeGrabbed + "Why?" + reasonStor;
	//The other setTimeout here is for DoneQ
	setTimeout(fillSplashDoneQ, durInMiliSeconds);
}

function fillSplashDoneQ() {
	cirSize = 30;
	var timeGrabbed = timeGrab();
	fillTextDoneQ = "Is " + nameStor + " task done? " + timeGrabbed;
	removeElements();
	doneQOptions();
}


function doneQOptions() {
  print ("Counter doneQOptions"+doneQCounter);
	if (doneQCounter > 0) {
		// var confirmNum = doneQcounter +1;
		var timeGrabbed = timeGrab();
		doneQOptionsFill = "Confirm #" + (doneQCounter + 1) + " at " + timeGrabbed;
	}

	createP("Is the Task " + nameStor + " done?"); 
	createP("If no, click 'Nope, not yet' and a timer will trigger.");
	createP("Otherwise hit yes and celebrate task done!");
	button = createElement('button', 'Yep, done!');
	button.mousePressed(confirmedDone);
	button = createElement('button', 'Nope,not yet.');
	button.mousePressed(notDoneYet);
}

					function confirmedDone() {
						removeElements();
						createP("Congrats on completing task "+nameStor);
					
					}

					function notDoneYet() {
						print("doneQIntStor = "+doneQIntStor);
						var minToMiliInt = ((doneQIntStor)*60)*1000
						print("minToMiliInt = "+minToMiliInt);
						setTimeout(doneQOptions, minToMiliInt);
						doneQCounter = doneQCounter+1;
					}


function draw() {
	// 	Make canvas, then display if something was submitted
	// 	show ellipse, and run show' function, creates a js.text to show
	background(150);
	fill(255);

	var timeGrabbed = timeGrab();
	text("ongoing " + timeGrabbed, 290, 275);

	if (nameStor) {
		text("task is -->" + nameStor + "! wait " + durTimeStor + " minutes", 10, 10);
	}

	ellipse(100, 100, cirSize, cirSize);
	showSubmittal();
	showSplashStartAlarm();
	showSplashDoneQ();
	doneQOptionsSplash();

}

function showSubmittal() {
	text(fillTextSubmit, 50, 30);
}

function showSplashStartAlarm() {
	text(fillTextStartAlarm, 25, 50, 245, 150);
}

function showSplashDoneQ() {
	text(fillTextDoneQ, 50, 150, 245, 150);
}

function doneQOptionsSplash() {
	text(doneQOptionsFill, 50, 250, 245, 150);
}




function taskInput() {

	createP("Task:"); //___TASK
	taskName = createInput('taskyTask');

	createP("Duration in min"); //___DURATION
	durTimeInput = createInput('.1');

	createP("Starting when?"); //___INPUTS FOR START TIME

	lh = hour();
	lm = minute();
	ls = second();

	startTimeInputH = createInput(lh);
	startTimeInputM = createInput(lm);
	startTimeInputS = createInput(ls);

	createP("Reason(s)"); //___REASON(S) FOR TASK
	reasonInput = createInput("Here's reason why");

	//createP("Endtime (if separate from end of duration)"); //eventually adding
	//endTimeInput = createInput("put time here hh:mm:ss");

	createP("'Check on you' min intervals"); //___DONE? INTERVALS
	doneQIntInput = createInput(".1");

	button = createElement('button', 'Submit');
	button.mousePressed(submitting);
}


function timeGrab() {
	var tgh = hour();
	var tgm = minute();
	var tgs = second();
	var pm;
	if (tgh >= 12) {
		pm = "p"
	}
	else {pm= " ";}
	if (tgh > 12) {
		tgh = tgh - 12;
	}
	if (tgh < 10) {
		tgh = "0" + tgh;
	}
	if (tgm < 10) {
		tgm = "0" + tgm;
	}
	if (tgs < 10) {
		tgs = "0" + tgs;
	}
	var stringOfTime = tgh + ":" + tgm + ":" + tgs + pm
	return stringOfTime;
}

// function timeGrab(number){
// 	var tgh = hour();
// 	var tgm = minute();
// 	var tgs = second();

// 	var stringOfTime = tgh+":"+tgm+":"+tgs+pm
// 	return stringOfTime;
// }