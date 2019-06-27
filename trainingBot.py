import random
from parseFunction import parser

def cfd(tokenisedText):
    
    file = open("trainingData.txt","r")
    
    answersArr = []
    questionsArr = []
    
    processedAnswers = [[]]
    answersFrequency = []
    
    lineNb = 0
    index = -1
    
    for line in file.readlines():
        lineNb += 1
        
        if lineNb%2 == 0:
            answersArr.append(line.strip('\n'))
        
        else:
            questionsArr.append(line.strip('\n'))
        
    frequenciesToken = []

    for token in tokenisedText:
        for question in questionsArr:
            
            questionParsed = parser()
            questionParsed.text = question

            frequency = 0
            
            if token in questionParsed.parse():

                frequency = 1/(len(questionParsed.parse()))
                frequenciesToken.append((frequency,questionsArr.index(question)))
    
    for answer in answersArr:
        index += 1
        if len(frequenciesToken)-1 >= index:
            processedAnswers.append([answer,frequenciesToken[index]])
        
        else:
            processedAnswers.append([answer,''])
    
    if len(frequenciesToken) != 0:

       for answer in answersArr:
            answerFrequency = 0
           
            for frequency in frequenciesToken:
 
                if answersArr.index(answer) == frequency[1]:
                    answerFrequency += frequency[0]

            answersFrequency.append(answerFrequency)
    
    if len(answersFrequency) != 0:
        bestAnswer = answersArr[answersFrequency.index(max(answersFrequency))]
       
    
    else:
        bestAnswer = random.choice(answersArr)
        
    return processedAnswers,bestAnswer