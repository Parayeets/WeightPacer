# My Personal Project

## WeightPacer

A *bulleted* list:

***What will the application do?***
- The application will be a weight pacer that you can measure your own mass every day, 
  then record your mass in the interface (and logs it in the list of masses that you 
  inputted throughout your campaign). We will follow this process until you reach your goal. 
    
  I will ***try*** to implement a system that makes the user adhere to a set rate of losing weight. 
  To add on to this, say after some time if you're not losing enough weight or, you're losing 
  weight too fast, the system will try to post some medical related advice to help them.

***Who will use it?***
- Anyone who wants to get involved in a campaign to lose weight, can all use this program. 
  Anyone that wants to make a change in their lifestyle or improve their quality of life would 
  be more than welcome to use this program. Physical activity and healthy eating is a must-have 
  for each person's daily routine. 
  
  While I will only just focus on just the pace of the loss of weight itself, this 
  program can be further developed by including factors like what you eat and how you eat, as well as 
  how much exercise (including what kind of exercise) did one do each day to give the user a more 
  accurate rate of loss/gain or maintaining mass. I can also factor in things like height and age 
  later on to help have a more accurate program for the user.

***Why is this project of interest to you?***
- The reason why I chose to take on this kind of project is that I too also have tried 
  to have a weight loss program for myself a few times. While I have been able to lose
  weight, on the other hand there hasn't been any resources that has been accessible and 
  not behind a paywall. When I try to log down my weight loss, it can be a really cumbersome
  and a tough task. This program will be created to make the process of focusing on one's 
  weight pacer program more streamlined, so they can stick to focusing on their campaign!

An example of text with **bold** and *italic* fonts.  Note that the IntelliJ markdown previewer doesn't seem to render 
the bold and italic fonts correctly but they will appear correctly on GitHub.

## User Stories
- As a user, I want to be able to have a campaign to lose weight. (check)
- As a user, I want to be able to include a daily record today's mass to a list of my masses from previous days (records). 
                            // (create new class called records)
- As a user, I want to have an option to delete my campaign after I met my goal. 
- As a user, I want to be able to see my list of masses. 
- As a user, I want to be able to see my analytics. 
- As a user, I want to save my records.
- As a user, I want to load my records.

// (first focus on how everything works then include people)
// class called daily record --> has weight, day, etc. 
// class called records (record book)



##Phase 4: Task 2
Test and design a class in your model package that is robust.  
You must have at least one method that throws a checked exception.  
You must have one test for the case where the exception is expected 
and another where the exception is not expected.

- The class that has been made robust is my ***user*** class and throws a checked exception.
- I throw an IncorrectInputException if the user inputs a final desired mass that's greater or equal to initial mass.

##Phase 4: Task 3
What I could have done to refactor and streamline my code is that I could have had my IntroSound class be within the 
GUI instead of it being its own class. Especially since I am only playing one sound. Otherwise, I tried to split all
functionalities into their own classes to the best of my ability so I do not think I could have done much to 
refactor my classes but I could have worked on some other areas, namely my fields.

My field naming has been somewhat inconsistent throughout my program and the first thing I would do to increase the
readability of my project is to rename most of my fields to the same thing, unless it really needed to be 
named as something else.