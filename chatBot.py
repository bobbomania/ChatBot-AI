from trainingBot import cfd
from parseFunction import parser

questionObj = parser()
file = open("trainingData.txt","r+")
meta = open("meta.txt","r+")

meta = open("meta.txt","r+")

questionObj.text = meta.readline().strip('\n')
tokenisedText = questionObj.parse()
print(questionObj.text)

bestAnswer = cfd(tokenisedText)[1]

meta.close()
open('meta.txt', 'w').close()

meta = open("meta.txt","w")

print(bestAnswer)
meta.write(bestAnswer + '\n')

meta.close()
