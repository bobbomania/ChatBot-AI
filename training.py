from parseFunction import parser

qu = parser()
qu.text = (str(input("")))
print(qu.parse())
    
file = open("trainingData.txt","r")
answers = open("trainingDataAnswers","w")
questions = open("trainingDataQuestions","w")
lineNb = 0

for line in file.readlines():
    lineNb += 1
    
    if lineNb%2 == 0:
        answers.write(line)
    
    else:
        questions.write(line)