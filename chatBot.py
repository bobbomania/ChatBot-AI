from trainingBot import cfd
from parseFunction import parser

questionObj = parser()
file = open("trainingData.txt","r+")

while True:
    questionObj.text = str(input("Any Questions? "))
    tokenisedText = questionObj.parse()
    
    bestAnswer = cfd(tokenisedText)
    if not bestAnswer[2]:
        print(bestAnswer[1])
    
    elif bestAnswer[2]:
        print(bestAnswer[1])
        satisfied = str(input('This response was randomised.Are you satisfied with the results? '))
        if 'yes' not in satisfied.lower():
        
            correctAnswer = str(input('Then answer us with a correct answer so that next time we get it right: '))

            file.write(questionObj.text + "\n")
            file.write(correctAnswer + "\n")
            file.close()
            