# Doldam Foundation System

## Specification and Requirements
- be able to log in as a patient, doctor or admin
- patients inputs their name, age, phone number
- patients can also book appointments, and choose their doctor and date
- doctors can view their appointments and add notes
- admins can view all patients and doctors and their appointments
- admins can be access using admin as username and a passwrd and no longer a button in log in page
### Doctors page requirements
- can log out and go to landing page
- can change password
### Admin page requirements
- add and remove doctors
    * can let new doctors input their infos
- clear patients datas
- can log out and go to landing page

## Research and Design
- store datas in files
- admins has username and passwords to log in
- jcombobox for dropdown option
- checkout properties in java for file inputs
- store a patient or a doctor in a file and group them into one folder, file system, data management
- [see ui design...](https://www.figma.com/design/EdEPhH4b5q3pklxU35CCv0/Doldam-Foundation?node-id=0-1&t=ekH1hfgnL4D6Qpsm-1)
- jframe
    * jbutton, anonymous class for action event
    * jtextfield, .getText
    * jpasswordfield, .getText and convert to string

## [See log](log.md)