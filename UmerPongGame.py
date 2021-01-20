
#   Umer Ahmad
#   September 29, 2017
#   This program is an imitation of the popular Pong Game.
import pygame
import sys
import random
pygame.init()

screenSize = (800,600)
screen = pygame.display.set_mode(screenSize,0)
pygame.display.set_caption ("Pong Game")

WHITE = (255,255,255)
GREEN = (0,255,0)
PURPLE = (128,0,128)
BLUE = (0,0,255)
RED = (255,0,0)
YELLOW = (255,255,0)
BLACK =(0,0,0)
GRAY =(128,128,128)


screen.fill(BLACK)
pygame.display.update()


fontTitle = pygame.font.SysFont("arial",70)
fontSubTitle = pygame.font.SysFont("arial",16)


clock = pygame.time.Clock()
FPS = 115


xCenter = screen.get_width()/2
yCenter = screen.get_height()/2

xCenterTitle = screen.get_width()/2
yCenterTitle = screen.get_height()/2 - 200

xCenterSubtitle = screen.get_width()/2
yCenterSubtitle = screen.get_height()/2 + 100

lineHeight = 100
lineWidth = 10

x = 25
y = 300

x2 = 775
y2 = 300

dy = 0
dy2 = 0



ballRadius = 17

ballX = screen.get_width()/2
ballY = screen.get_height()/2

ballDx = random.randint(2,5)
ballDy = random.randint(2,5)

scorePlayer1 = 0
scorePlayer2 = 0

intro = True
intro2 = False
singleplayerDifficulties = False
singleplayerNormal = False
singleplayerDynamic = False
multiplayer = False
scoreScreenMultiplayer = False
scoreScreenSingleplayer = False

while intro:
        for event in pygame.event.get():
            if event.type ==pygame.QUIT:
                        intro = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                        intro = False
                if event.key == pygame.K_e:
                        intro = False
                        intro2 = True

        screen.fill(BLACK)          
        text3 = fontTitle.render("PONG", True, WHITE)
        text_rect3 = text3.get_rect(center=(xCenter, yCenter))
        screen.blit(text3, text_rect3)
        
        text4 = fontSubTitle.render("Press E to begin and Q at anytime to quit", True, WHITE)
        text_rect4 = text4.get_rect(center=(xCenterSubtitle, yCenterSubtitle))
        screen.blit(text4, text_rect4)

        pygame.display.update()
	

while intro2:
        for event in pygame.event.get():
                if event.type == pygame.QUIT:
                        intro2 = False
                elif event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_q:
                                intro2 = False
                        if event.key == pygame.K_1:
                                intro2 = False
                                singleplayerDifficulties = True
                        if event.key == pygame.K_2:
                                intro2 = False
                                multiplayer = True
        screen.fill(BLACK)
        text5 = fontTitle.render("Welcome to the Pong Game!", True, WHITE)
        text_rect5 = text5.get_rect(center=(xCenterTitle, yCenterTitle))
        screen.blit(text5, text_rect5)

        text6 = fontSubTitle.render("There will be two paddles on the screen, and your goal is to beat your opponent by sending the ball past him.", True, WHITE)
        text_rect6 = text6.get_rect(center=(xCenter, yCenter))
        screen.blit(text6, text_rect6)

        text7 = fontSubTitle.render("The left side uses W and S to control, and the right uses UP and DOWN arrow keys.", True, WHITE)
        text_rect7 = text7.get_rect(center=(xCenter, yCenter+25))
        screen.blit(text7, text_rect7)

        text8 = fontSubTitle.render("Press 1 for single player and 2 for two players.", True, WHITE)
        text_rect8 = text8.get_rect(center=(xCenter, yCenter+50))
        screen.blit(text8, text_rect8)

        pygame.display.update()

                
while singleplayerDifficulties:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                    singleplayerDifficulties = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    singleplayerDifficulties = False
                if event.key == pygame.K_1:
                    singleplayerDifficulties = False
                    singleplayerNormal = True
                if event.key == pygame.K_2:
                    singleplayerDifficulties = False
                    singleplayerDynamic = True
                    

        screen.fill(BLACK)
        text9 = fontTitle.render("Press 1 for Normal", True, WHITE)
        text_rect9 = text9.get_rect(center=(xCenterTitle, yCenterTitle))
        screen.blit(text9, text_rect9)

        
        text11 = fontTitle.render("Press 2 for Hard/Dynamic", True, WHITE)
        text_rect11 = text11.get_rect(center=(xCenterTitle, yCenterTitle+300))
        screen.blit(text11, text_rect11)

        text12 = fontSubTitle.render("(Random speeds and gets overall more faster the more you play)", True, WHITE)
        text_rect12 = text12.get_rect(center=(xCenterTitle, yCenterTitle+350))
        screen.blit(text12, text_rect12)

        pygame.display.update()

while singleplayerNormal:
        for event in pygame.event.get():
            if event.type ==pygame.QUIT:
                    singleplayerNormal = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    singleplayerNormal = False
                elif event.key == pygame.K_w:
                    dy = -5
                elif event.key == pygame.K_s:
                    dy = 5
                elif event.key == pygame.K_ESCAPE:
                    singleplayerNormal = False
                    scoreScreenSingleplayer = True

            if event.type == pygame.KEYUP:
                if event.key == pygame.K_w:
                    dy = 0
                elif event.key == pygame.K_s:
                    dy = 0
                    

        screen.fill(BLACK)
        clock.tick(FPS)

        dy2Easy = ballDy + 1
        
        oldY = y
        oldY2 = y2

        y = y + dy
        y2 = y2 + dy2Easy

    
        oldBallY = ballY
        oldBallX = ballX
        
        
        ballY = ballY + ballDy
        ballX = ballX + ballDx

   
      
        if (ballX>=783):		
                scorePlayer2 = scorePlayer2+1
                ballX = screen.get_width()/2
                ballY = screen.get_height()/2
                ballDx = random.randint(-3,-2)
                ballDy = random.randint(-3,-2)
        elif (ballX<=17):		
                scorePlayer1 = scorePlayer1+1
                ballX = screen.get_width()/2
                ballY = screen.get_height()/2
                ballDx = random.randint(2,3)
                ballDy = random.randint(2,3)
        if (ballY>=583):		
                ballDy = -ballDy
        elif (ballY<=17):		
                ballDy = -ballDy


        if (y>=500):		
            y = oldY
        elif (y<=1):		
            y = oldY
            
        if (y2>=500):		
            y2 = oldY2
        elif (y2<=1):		
            y2 = oldY2





        if ballX - ballRadius < x + lineWidth:
                if ballY > y:
                        if ballY < y + lineHeight:
                                ballDx = -ballDx

        if ballX + ballRadius > x2:
                if ballY > y2:
                        if ballY < y2 + lineHeight:
                                ballDx = -ballDx


               
        pygame.draw.circle (screen, WHITE, (ballX, ballY), ballRadius,0)
        
        pygame.draw.line(screen, WHITE, [400, 0], [400, 600], 2)
        
        pygame.draw.rect(screen, RED, [x, y, lineWidth, lineHeight])
        pygame.draw.rect(screen, BLUE, [x2, y2, lineWidth, lineHeight])

        text18 = fontTitle.render(str(scorePlayer2), True, WHITE)
        text_rect18 = text18.get_rect(center=(300, 50))
        screen.blit(text18, text_rect18)

        text19 = fontTitle.render(str(scorePlayer1), True, WHITE)
        text_rect19 = text19.get_rect(center=(500, 50))
        screen.blit(text19, text_rect19)

        text20 = fontSubTitle.render("Press ESC to end the game.", True, WHITE)
        text_rect20 = text20.get_rect(center=(710, 20))
        screen.blit(text20, text_rect20)
        
        
        
        pygame.display.update()


while singleplayerDynamic:
        for event in pygame.event.get():
            if event.type ==pygame.QUIT:
                    singleplayerDynamic = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    singleplayerDynamic = False
                elif event.key == pygame.K_w:
                    dy = -5
                elif event.key == pygame.K_s:
                    dy = 5
                elif event.key == pygame.K_ESCAPE:
                    singleplayerDynamic = False
                    scoreScreenSingleplayer = True

            if event.type == pygame.KEYUP:
                if event.key == pygame.K_w:
                    dy = 0
                elif event.key == pygame.K_s:
                    dy = 0
                    

        screen.fill(BLACK)
        clock.tick(FPS)

        dy2Dynamic = ballDy + 1
        
        oldY = y
        oldY2 = y2

        y = y + dy
        y2 = y2 + dy2Dynamic

    
        oldBallY = ballY
        oldBallX = ballX
        
        
        ballY = ballY + ballDy
        ballX = ballX + ballDx

   
      
        if (ballX>=783):		
                scorePlayer2 = scorePlayer2+1
                ballX = screen.get_width()/2
                ballY = screen.get_height()/2
                ballDx = random.randint(-6,-5)
                ballDy = random.randint(-6,-5)
                FPS = FPS + 10
        elif (ballX<=17):		
                scorePlayer1 = scorePlayer1+1
                ballX = screen.get_width()/2
                ballY = screen.get_height()/2
                ballDx = random.randint(5,6)
                ballDy = random.randint(5,6)
                FPS = FPS + 10
        if (ballY>=583):		
                ballDy = -ballDy
        elif (ballY<=17):		
                ballDy = -ballDy


        if (y>=500):		
            y = oldY
        elif (y<=1):		
            y = oldY
            
        if (y2>=500):		
            y2 = oldY2
        elif (y2<=1):		
            y2 = oldY2





        if ballX - ballRadius < x + lineWidth:
                if ballY > y:
                        if ballY < y + lineHeight:
                                ballDx = -ballDx

        if ballX + ballRadius > x2:
                if ballY > y2:
                        if ballY < y2 + lineHeight:
                                ballDx = -ballDx


               
        pygame.draw.circle (screen, WHITE, (ballX, ballY), ballRadius,0)
        
        pygame.draw.line(screen, WHITE, [400, 0], [400, 600], 2)
        
        pygame.draw.rect(screen, RED, [x, y, lineWidth, lineHeight])
        pygame.draw.rect(screen, BLUE, [x2, y2, lineWidth, lineHeight])

        text18 = fontTitle.render(str(scorePlayer2), True, WHITE)
        text_rect18 = text18.get_rect(center=(300, 50))
        screen.blit(text18, text_rect18)

        text19 = fontTitle.render(str(scorePlayer1), True, WHITE)
        text_rect19 = text19.get_rect(center=(500, 50))
        screen.blit(text19, text_rect19)

        text20 = fontSubTitle.render("Press ESC to end the game.", True, WHITE)
        text_rect20 = text20.get_rect(center=(710, 20))
        screen.blit(text20, text_rect20)
        
        
        
        pygame.display.update()

while scoreScreenSingleplayer:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                    scoreScreenSingleplayer = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    scoreScreenSingleplayer = False
                elif event.key == pygame.K_ESCAPE:
                    scoreScreenSingleplayer = False
                    intro = True





        screen.fill(BLACK)

        
        text14 = fontTitle.render(str(scorePlayer2), True, RED)
        text_rect14 = text14.get_rect(center=(xCenterTitle - 200, yCenterTitle))
        screen.blit(text14, text_rect14)

        
        text15 = fontTitle.render(str(scorePlayer1), True, BLUE)
        text_rect15 = text15.get_rect(center=(xCenterTitle + 200, yCenterTitle))
        screen.blit(text15, text_rect15)

        text16 = fontTitle.render("Thanks for playing!", True, WHITE)
        text_rect16 = text16.get_rect(center=(xCenterTitle, yCenterTitle+200))
        screen.blit(text16, text_rect16)

        text17 = fontSubTitle.render("Press ESCAPE to quit.", True, WHITE)
        text_rect17 = text17.get_rect(center=(xCenterTitle, yCenterTitle+300))
        screen.blit(text17, text_rect17)
        
        pygame.display.update()
        

while multiplayer:
        for event in pygame.event.get():
            if event.type ==pygame.QUIT:
                    multiplayer = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    multiplayer = False
                elif event.key == pygame.K_w:
                    dy = -5
                elif event.key == pygame.K_s:
                    dy = 5
                elif event.key == pygame.K_UP:
                    dy2 = -5
                elif event.key == pygame.K_DOWN:
                    dy2 = 5
                elif event.key == pygame.K_ESCAPE:
                    multiplayer = False
                    scoreScreenMultiplayer = True

            if event.type == pygame.KEYUP:
                if event.key == pygame.K_w or event.key == pygame.K_UP:
                    dy = 0
                    dy2 = 0
                elif event.key == pygame.K_s or event.key == pygame.K_DOWN:
                    dy = 0
                    dy2 = 0

        screen.fill(BLACK)
        clock.tick(FPS)
        
        oldY = y
        oldY2 = y2

        y = y + dy
        y2 = y2 + dy2

    
        oldBallY = ballY
        oldBallX = ballX

    
        ballY = ballY + ballDy
        ballX = ballX + ballDx

   
      
        if (ballX>=783):		
                scorePlayer2 = scorePlayer2+1
                ballX = screen.get_width()/2
                ballY = screen.get_height()/2
                ballDx = random.randint(-5,-2)
                ballDy = random.randint(-5,-2)
        elif (ballX<=17):		
                scorePlayer1 = scorePlayer1+1
                ballX = screen.get_width()/2
                ballY = screen.get_height()/2
                ballDx = random.randint(2,5)
                ballDy = random.randint(2,5)
        if (ballY>=583):		
                ballDy = -ballDy
        elif (ballY<=17):		
                ballDy = -ballDy


        if (y>=500):		
            y = oldY
        elif (y<=1):		
            y = oldY
            
        if (y2>=500):		
            y2 = oldY2
        elif (y2<=1):		
            y2 = oldY2





        if ballX - ballRadius < x + lineWidth:
                if ballY > y:
                        if ballY < y + lineHeight:
                                ballDx = -ballDx

        if ballX + ballRadius > x2:
                if ballY > y2:
                        if ballY < y2 + lineHeight:
                                ballDx = -ballDx


               
        pygame.draw.circle (screen, WHITE, (ballX, ballY), ballRadius,0)
        
        pygame.draw.line(screen, WHITE, [400, 0], [400, 600], 2)
        
        pygame.draw.rect(screen, RED, [x, y, lineWidth, lineHeight])
        pygame.draw.rect(screen, BLUE, [x2, y2, lineWidth, lineHeight])

        text = fontTitle.render(str(scorePlayer2), True, WHITE)
        text_rect = text.get_rect(center=(300, 50))
        screen.blit(text, text_rect)

        text2 = fontTitle.render(str(scorePlayer1), True, WHITE)
        text_rect2 = text2.get_rect(center=(500, 50))
        screen.blit(text2, text_rect2)

        text13 = fontSubTitle.render("Press ESC to quit.", True, WHITE)
        text_rect13 = text13.get_rect(center=(710, 20))
        screen.blit(text13, text_rect13)
        
        
        
        pygame.display.update()
        

while scoreScreenMultiplayer:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                    scoreScreenMultiplayer = False
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    scoreScreenMultiplayer = False
                elif event.key == pygame.K_ESCAPE:
                    scoreScreenMultiplayer = False
                    intro = True





        screen.fill(BLACK)

        
        text14 = fontTitle.render(str(scorePlayer2), True, RED)
        text_rect14 = text14.get_rect(center=(xCenterTitle - 200, yCenterTitle))
        screen.blit(text14, text_rect14)

        
        text15 = fontTitle.render(str(scorePlayer1), True, BLUE)
        text_rect15 = text15.get_rect(center=(xCenterTitle + 200, yCenterTitle))
        screen.blit(text15, text_rect15)

        text16 = fontTitle.render("Thanks for playing!", True, WHITE)
        text_rect16 = text16.get_rect(center=(xCenterTitle, yCenterTitle+200))
        screen.blit(text16, text_rect16)

        text17 = fontSubTitle.render("Press ESCAPE to quit.", True, WHITE)
        text_rect17 = text17.get_rect(center=(xCenterTitle, yCenterTitle+300))
        screen.blit(text17, text_rect17)
        
        pygame.display.update()
	

pygame.quit()
sys.exit()

