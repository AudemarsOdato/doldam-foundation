# logs

## June 1, 2025
What I Did
- Fixed the remove a doctor button
- Added header for the side panel in admin and doctors page

What's Next
- Compile and submit the project
- Create a bug protocol, on what to do when stucked in a bug

What Broke or Got Weird
- Files being doubled when updating it in removing a doctor, caused by a missing dot "." before the txt in the reset function. Thus not reseting it properly
- The problem of not updating the files properly from the previous day are caused by not appending it. The parameter in filewriter to set the append to true are missing.

## May 31, 2025
What I Did
- Added functionality in adding new doctor button

What's Next
- Finish and fix remove doctor button

What Broke or Got Weird
- Files being overriden, all doctor infos being removed except the last one instead of just the doctor to be removed

## May 29, 2025
What I Did
- Added functionalities in admin side panel buttons for patient and doctor display
- Fixed issues in updating patients display after erasing all records
- Fixed and improved the booking page's date input

What's Next
- Add functionality in adding and removing a doctor

Quick Insight
- Use JOptionPane gui for adding and removing doctor buttons
```
// Find the doctor to delete
Doctor doctorToDelete = null;
for (Doctor doctor : allDoctors) {
    if (doctor.getName().equals("Name of doctor to delete")) {
        doctorToDelete = doctor;
        break;
    }
}

// Remove the doctor from the list
if (doctorToDelete != null) {
    allDoctors.remove(doctorToDelete);
}

allDoctors.removeIf(doctor -> doctor.getName().equals("Name of doctor to delete"));
```

What Broke or Got Weird
- The patients display table goes weird when empty. Try unpacking it

## May 26, 2025
What I Did
- Added side panels for admin and doctors page
- Fixed header in doctors page
- Added the buttons for sidepanel in admin page

What's Next
- Add functionalities for buttons in admin page

Productivity Score
- 3/5 Quick code

## May 25, 2025
What I Did
- Added log out functionality in admin and doctors page
- Added add new booking in doctors page
- Fixed ui flow in booking page and afterbooking page in doctors page

What's Next
- Add side panel for admin and doctors page
- Put the buttons on the side panel
- In Admin page, add all the buttons for:
    viewing all patients/doctors,
    remove or erasing all patient database,
    adding/creating new doctor
    remove a quiting doctors log in info

Productivity Score
- 4/5 for doing it for such a short time

## May 23, 2025
What I Did
- Created a structred file system for personels, admin and doctors infos
- Implemented the new file system for personels in admin, booking, and log in page
- Organized files by creating appropriate folders

What's Next
- Add log out function in admin and doctors page
- Add booking functionality in doctors page

What Broke or Got Weird
- Forgot to remove the line for adding the admin log in info after creating another for loop for it

Quick Insight
- Check the log in functionality of admins. 

## May 22, 2025
What I Did
- Changed the date input in booking page into a dropdown list with 3 days range
- Added icon logo to all pages

What's Next
- Make database for all doctors info, their names, usernames, passwords
- Be able to log out in doctors page and admin page, and return to the landing page

Quick Insight
- Check main file for to do list
