#   Umer Ahmad
#   October 12, 2017
#   This program is an imitation of the Snake Game
import pygame
import sys
pygame.init()
screenSize = (800,600)
screen = pygame.display.set_mode((screenSize),0)
pygame.display.set_caption("UserSnakeGame")

WHITE = (255,255,255)
GREEN = (0,255,0)
PURPLE = (128,0,128)
BLUE = (0,0,255)
RED = (255,0,0)
YELLOW = (255,255,0)
BLACK =(0,0,0)
GRAY =(128,128,128)

screen.fill(WHITE)
pygame.display.update()

clock = pygame.time.Clock()
FPS = 115

fontTitle = pygame.font.SysFont("arial",70)
fontTitle2 = pygame.font.SysFont("arial", 48)
fontSubTitle = pygame.font.SysFont("arial",16)


xCenter = screen.get_width()/2
yCenter = screen.get_height()/2

xCenterTitle = screen.get_width()/2
yCenterTitle = screen.get_height()/2 - 200

xCenterSubtitle = screen.get_width()/2
yCenterSubtitle = screen.get_height()/2 + 100

x = 100
y = 300

x2 = 700
y2 = 300

dx = 2
dy = 0

dx2 = -2
dy2 = 0

ballRadius = 7
ballRadius2 = 7
             

frames = 0
seconds = 0
score = 0


main = True
intro = False
intro2 = False
singleplayerDifficulties = False
scorescreenSingleplayer = False
scorescreenMultiplayer = False
multiplayer = False
singleplayerNormal = False
singleplayerDynamic = False


while main:
        score = 0
        frames = 0
        intro = True
        while intro:
                for event in pygame.event.get():
                    if event.type ==pygame.QUIT:
                                intro = False
                                main = False
                    elif event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_q:
                                intro = False
                                main = False
                        if event.key == pygame.K_e:
                                intro = False
                                intro2 = True

                x = 100
                y = 300

                x2 = 700
                y2 = 300
                
                dx = 2
                dy = 0

                dx2 = -2
                dy2 = 0

                
                screen.fill(WHITE)          
                text = fontTitle.render("SNAKE", True, GREEN)
                text_rect = text.get_rect(center=(xCenter, yCenter))
                screen.blit(text, text_rect)
                
                text2 = fontSubTitle.render("Press E to Start", True, BLUE)
                text_rect2 = text2.get_rect(center=(xCenterSubtitle, yCenterSubtitle))
                screen.blit(text2, text_rect2)

                text3 = fontSubTitle.render("Press Q to force quit at any time.", True, BLUE)
                text_rect3 = text3.get_rect(center=(xCenterSubtitle, yCenterSubtitle+50))
                screen.blit(text3, text_rect3)
                
                pygame.display.update()

        while intro2:
                for event in pygame.event.get():
                    if event.type ==pygame.QUIT:
                                intro2 = False
                                main = False
                    elif event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_q:
                                intro2 = False
                                main = False
                        if event.key == pygame.K_1:
                                 intro2 = False
                                 singleplayerDifficulties = True
                        if event.key == pygame.K_2:
                                 intro2 = False
                                 multiplayer = True

                x = 100
                y = 300
                dx = 2
                dy = 0
                FPS = 115

                
                screen.fill(WHITE)          
                text = fontTitle.render("Welcome to the SNAKE game!", True, GREEN)
                text_rect = text.get_rect(center=(xCenter, yCenter-200))
                screen.blit(text, text_rect)

                pygame.draw.line(screen, GREEN, [xCenter - 200, yCenter], [xCenter + 200, yCenter], 20)
                pygame.draw.circle(screen, RED,(xCenter + 180,yCenter - 5), 3,0)
                pygame.draw.circle(screen, RED,(xCenter + 180,yCenter + 5), 3,0)
                pygame.draw.polygon(screen, RED, [[xCenter + 200, yCenter - 7], [xCenter + 200, yCenter +7],[xCenter + 220, yCenter]], 0)
                pygame.draw.circle(screen, GREEN,(xCenter -202,yCenter + 1 ), 10,0)

                text1 = fontSubTitle.render("Use WASD for controls, don`t run into yourself or the enemy and try to last as long as possible!", True, BLUE)
                text_rect1 = text1.get_rect(center=(xCenterSubtitle, yCenterSubtitle - 50))
                screen.blit(text1, text_rect1)
                
                text2 = fontSubTitle.render("Press 1 for Singleplayer and 2 for Multiplayer", True, BLUE)
                text_rect2 = text2.get_rect(center=(xCenterSubtitle, yCenterSubtitle))
                screen.blit(text2, text_rect2)

                text3 = fontSubTitle.render("Press Q to force quit at any time.", True, BLUE)
                text_rect3 = text3.get_rect(center=(xCenterSubtitle, yCenterSubtitle+50))
                screen.blit(text3, text_rect3)

                text4 = fontSubTitle.render("(WASD for left/blue and ARROW keys for right/red.)", True, BLUE)
                text_rect4 = text4.get_rect(center=(xCenterSubtitle + 225, yCenterSubtitle + 15))
                screen.blit(text4, text_rect4)
                
                pygame.display.update()
                
        while singleplayerDifficulties:
                for event in pygame.event.get():
                    if event.type == pygame.QUIT:
                                singleplayerDifficulties = False
                                main = False
                    elif event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_q:
                                singleplayerDifficulties = False
                                main = False
                        if event.key == pygame.K_1:  
                                 singleplayerDifficulties = False
                                 singleplayerNormal = True
                        if event.key == pygame.K_2:
                                 singleplayerDifficulties = False
                                 singleplayerDynamic = True

                x = 100
                y = 300
                dx = 2
                dy = 0

                
                screen.fill(WHITE)          
                text = fontTitle.render("Press 1 for Normal", True, GREEN)
                text_rect = text.get_rect(center=(xCenter, yCenter-200))
                screen.blit(text, text_rect)

                text3 = fontSubTitle.render("1x score multiplier.", True, BLUE)
                text_rect3 = text3.get_rect(center=(xCenterSubtitle, yCenterSubtitle - 250))
                screen.blit(text3, text_rect3)

                text = fontTitle.render("Press 2 for Dynamic/Hard", True, GREEN)
                text_rect = text.get_rect(center=(xCenter, yCenter+ 150))
                screen.blit(text, text_rect)

                text3 = fontSubTitle.render("1.5x score multiplier and goes faster as time goes by. ", True, BLUE)
                text_rect3 = text3.get_rect(center=(xCenterSubtitle, yCenterSubtitle+125))
                screen.blit(text3, text_rect3)


                text3 = fontSubTitle.render("Press Q to force quit at any time.", True, BLUE)
                text_rect3 = text3.get_rect(center=(xCenterSubtitle, yCenterSubtitle+150))
                screen.blit(text3, text_rect3)
                
                pygame.display.update()
                
        while singleplayerNormal:
            for event in pygame.event.get():
                if event.type ==pygame.QUIT:
                    singleplayerNormal = False
                    main = False
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_q:
                        singleplayerNormal = False
                        main = False
                    if event.key == pygame.K_ESCAPE:
                        singleplayerNormal = False
                        main = True
                    if event.key == pygame.K_a:
                        dx = -2
                        dy = 0
                    elif event.key == pygame.K_d:
                        dx = 2
                        dy = 0
                    elif event.key == pygame.K_w:
                        dx = 0
                        dy = -2
                    elif event.key == pygame.K_s:
                        dx = 0
                        dy = 2
                   

            

            if frames == 0:
                screen.fill(WHITE)
            clock.tick(FPS)
            seconds = frames%FPS
            if seconds == 0:
                score = score +1
            frames = frames +1
            
            oldX = x
            oldY = y
            
            x = x + dx
            y = y + dy


            
            if (x>=790):		
                x = oldX
            elif (x<=10):		
                x = oldX
            if (y>=590):		
                y = oldY
            elif (y<=10):		
                y = oldY

            if dx>0:
                colour = screen.get_at((x+ballRadius+1,y))
                if colour == BLUE:
                    singleplayerNormal = False
                    scorescreenSingleplayer = True

            if dx<0:
                colour = screen.get_at((x-ballRadius-1,y))
                if colour == BLUE:
                    singleplayerNormal = False
                    scorescreenSingleplayer = True

            if dy>0:
                colour = screen.get_at((x,y+ballRadius+1))
                if colour == BLUE:
                    singleplayerNormal = False
                    scorescreenSingleplayer = True

            if dy<0:
                colour = screen.get_at((x,y-ballRadius-1))
                if colour == BLUE:
                    singleplayerNormal = False
                    scorescreenSingleplayer = True

            if dx == 2 or -2:
                score = score *1

       
            pygame.draw.line(screen, BLUE, [0, 0], [800, 0], 10)
            pygame.draw.line(screen, BLUE, [800, 0], [800, 600], 10)
            pygame.draw.line(screen, BLUE, [800, 600], [0, 600], 10)
            pygame.draw.line(screen, BLUE, [0, 600], [0, 0], 10)
            
            pygame.draw.circle(screen, BLUE,(x,y), ballRadius,0)

            pygame.draw.circle(screen, WHITE,(xCenterTitle + 390, yCenterTitle - 85), ballRadius,0)

            text = fontSubTitle.render(str(score), True, PURPLE)
            text_rect = text.get_rect(center=(xCenterTitle + 390, yCenterTitle - 85))
            screen.blit(text, text_rect)
            
            pygame.display.update()


        while singleplayerDynamic:
            for event in pygame.event.get():
                if event.type ==pygame.QUIT:
                    singleplayerDynamic = False
                    main = False
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_q:
                        singleplayerDynamic = False
                        main = False
                    if event.key == pygame.K_ESCAPE:
                        singleplayerDynamic = False
                        main = True
                    if event.key == pygame.K_a:
                        dx = -2
                        dy = 0
                        FPS = FPS +5
                    elif event.key == pygame.K_d:
                        dx = 2
                        dy = 0
                        FPS = FPS +5
                    elif event.key == pygame.K_w:
                        dx = 0
                        dy = -2
                        FPS = FPS +5
                    elif event.key == pygame.K_s:
                        dx = 0
                        dy = 2
                        FPS = FPS +5
                   

            

            if frames == 0:
                screen.fill(WHITE)
            clock.tick(FPS)
            seconds = frames%FPS
            if seconds == 0:
                score = score +1 * 1.5
            frames = frames +1
            
            oldX = x
            oldY = y
            
            x = x + dx
            y = y + dy


            
            if (x>=790):		
                x = oldX
            elif (x<=10):		
                x = oldX
            if (y>=590):		
                y = oldY
            elif (y<=10):		
                y = oldY

            if dx>0:
                colour = screen.get_at((x+ballRadius+1,y))
                if colour == BLUE:
                    singleplayerDynamic = False
                    scorescreenSingleplayer = True

            if dx<0:
                colour = screen.get_at((x-ballRadius-1,y))
                if colour == BLUE:
                    singleplayerDynamic = False
                    scorescreenSingleplayer = True

            if dy>0:
                colour = screen.get_at((x,y+ballRadius+1))
                if colour == BLUE:
                    singleplayerDynamic = False
                    scorescreenSingleplayer = True

            if dy<0:
                colour = screen.get_at((x,y-ballRadius-1))
                if colour == BLUE:
                    singleplayerDynamic = False
                    scorescreenSingleplayer = True

            
            pygame.draw.line(screen, BLUE, [0, 0], [800, 0], 10)
            pygame.draw.line(screen, BLUE, [800, 0], [800, 600], 10)
            pygame.draw.line(screen, BLUE, [800, 600], [0, 600], 10)
            pygame.draw.line(screen, BLUE, [0, 600], [0, 0], 10)
            
            pygame.draw.circle(screen, BLUE,(x,y), ballRadius,0)

            pygame.draw.circle(screen, WHITE,(xCenterTitle + 385, yCenterTitle - 80), 11,0)
        
            text = fontSubTitle.render(str(score), True, PURPLE)
            text_rect = text.get_rect(center=(xCenterTitle + 385, yCenterTitle - 80))
            screen.blit(text, text_rect)
            
            pygame.display.update()

        while scorescreenSingleplayer:
                for event in pygame.event.get():
                    if event.type ==pygame.QUIT:
                                scorescreenSingleplayer = False
                                main = False
                    elif event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_q:
                                scorescreenSingleplayer = False
                                main = False
                        if event.key == pygame.K_ESCAPE:
                                scorescreenSingleplayer = False
                                main = True
                                


                screen.fill(WHITE)          
                text = fontTitle.render("Thanks For Playing!", True, GREEN)
                text_rect = text.get_rect(center=(xCenter, yCenter))
                screen.blit(text, text_rect)

                text2 = fontSubTitle.render("Press Q to quit or ESCAPE to go back.", True, BLUE)
                text_rect2 = text2.get_rect(center=(xCenterSubtitle, yCenterSubtitle))
                screen.blit(text2, text_rect2)

                text3 = fontTitle2.render("You lasted :", True, BLUE)
                text_rect3 = text3.get_rect(center=(xCenterTitle - 150, yCenterTitle))
                screen.blit(text3, text_rect3)

                text4 = fontTitle2.render(str(score), True, BLUE)
                text_rect4 = text4.get_rect(center=(xCenterTitle, yCenterTitle))
                screen.blit(text4, text_rect4)

                text5 = fontTitle2.render("seconds!", True, BLUE)
                text_rect5 = text5.get_rect(center=(xCenterTitle + 125, yCenterTitle))
                screen.blit(text5, text_rect5)
            
                pygame.display.update()

        while multiplayer:
            for event in pygame.event.get():
                if event.type ==pygame.QUIT:
                    multiplayer = False
                    main = False
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_q:
                        multiplayer = False
                        main = False
                    if event.key == pygame.K_ESCAPE:
                        multiplayer = False
                        main = True
                    if event.key == pygame.K_a:
                        dx = -2
                        dy = 0
                    elif event.key == pygame.K_d:
                        dx = 2
                        dy = 0
                    elif event.key == pygame.K_w:
                        dx = 0
                        dy = -2
                    elif event.key == pygame.K_s:
                        dx = 0
                        dy = 2

                    if event.key == pygame.K_UP:
                        dx2 = 0
                        dy2 = -2
                    elif event.key == pygame.K_DOWN:
                        dx2 = 0
                        dy2 = 2
                    elif event.key == pygame.K_LEFT:
                        dx2 = -2
                        dy2 = 0
                    elif event.key == pygame.K_RIGHT:
                        dx2 = 2
                        dy2 = 0


                   

            



            if frames == 0:
                screen.fill(WHITE)
            clock.tick(FPS)
            seconds = frames%FPS
            if seconds == 0:
                score = score +1 * 1.5
            frames = frames +1

            
            oldX = x
            oldY = y

            oldX2 = x2
            oldY2 = y2
            
            x = x + dx
            y = y + dy

            x2 = x2 + dx2
            y2 = y2 + dy2

           
            
            if (x>=790):		
                x = oldX
            elif (x<=10):		
                x = oldX
            if (y>=590):		
                y = oldY
            elif (y<=10):		
                y = oldY

            if (x2>=790):		
                x2 = oldX2
            elif (x2<=10):		
                x2 = oldX2
            if (y2>=590):		
                y2 = oldY2
            elif (y2<=10):		
                y2 = oldY2

            if dx>0:
                colour = screen.get_at((x+ballRadius+1,y))
                if colour == BLUE:
                    multiplayer = False
                    scorescreenMultiplayer = True
                if colour == RED:
                    multiplayer = False
                    scorescreenMultiplayer = True

            if dx<0:
                colour = screen.get_at((x-ballRadius-1,y))
                if colour == BLUE:
                    multiplayer = False
                    scorescreenMultiplayer = True
                if colour == RED:
                    multiplayer = False
                    scorescreenMultiplayer = True

            if dy>0:
                colour = screen.get_at((x,y+ballRadius+1))
                if colour == BLUE:
                    multiplayer = False
                    scorescreenMultiplayer = True
                if colour == RED:
                    multiplayer = False
                    scorescreenMultiplayer = True

            if dy<0:
                colour = screen.get_at((x,y-ballRadius-1))
                if colour == BLUE:
                    multiplayer = False
                    scorescreenMultiplayer = True
                if colour == RED:
                    multiplayer = False
                    scorescreenMultiplayer = True





            if dx2>0:
                colour2 = screen.get_at((x2+ballRadius2+1,y2))
                if colour2 == BLUE:
                    multiplayer = False
                    scorescreenMultiplayer = True
                if colour2 == RED:
                    multiplayer = False
                    scorescreenMultiplayer = True

            if dx2<0:
                colour2 = screen.get_at((x2-ballRadius2-1,y2))
                if colour2 == BLUE:
                    multiplayer = False
                    scorescreenMultiplayer = True
                if colour2 == RED:
                    multiplayer = False
                    scorescreenMultiplayer = True

            if dy2>0:
                colour2 = screen.get_at((x2,y2+ballRadius2+1))
                if colour2 == BLUE:
                    multiplayer = False
                    scorescreenMultiplayer = True
                if colour2 == RED:
                    multiplayer = False
                    scorescreenMultiplayer = True

            if dy2<0:
                colour2 = screen.get_at((x2,y2-ballRadius2-1))
                if colour2 == BLUE:
                    multiplayer = False
                    scorescreenMultiplayer = True
                if colour2 == RED:
                    multiplayer = False
                    scorescreenMultiplayer = True

                    





            
            pygame.draw.line(screen, BLUE, [0, 0], [800, 0], 10)
            pygame.draw.line(screen, BLUE, [800, 0], [800, 600], 10)
            pygame.draw.line(screen, RED, [800, 600], [0, 600], 10)
            pygame.draw.line(screen, RED, [0, 600], [0, 0], 10)
            
            pygame.draw.circle(screen, BLUE,(x,y), ballRadius,0)
            pygame.draw.circle(screen, RED,(x2,y2), ballRadius2,0)

            
            pygame.display.update()


        while scorescreenMultiplayer:
                for event in pygame.event.get():
                    if event.type ==pygame.QUIT:
                                scorescreenMultiplayer = False
                                main = False
                    elif event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_q:
                                scorescreenMultiplayer = False
                                main = False
                        if event.key == pygame.K_ESCAPE:
                                scorescreenMultiplayer = False
                                main = True


                        if colour == BLUE:
                                text3 = fontTitle2.render("RED/RIGHT SIDE WINS", True, RED)
                                text_rect3 = text3.get_rect(center=(xCenterTitle - 150, yCenterTitle))
                                screen.blit(text3, text_rect3)
                        if colour == RED:
                                text3 = fontTitle2.render("RED/RIGHT SIDE WINS", True, RED)
                                text_rect3 = text3.get_rect(center=(xCenterTitle - 150, yCenterTitle))
                                screen.blit(text3, text_rect3)
                        if colour2 == BLUE:
                                text3 = fontTitle2.render("BLUE/LEFT SIDE WINS", True, BLUE)
                                text_rect3 = text3.get_rect(center=(xCenterTitle - 150, yCenterTitle))
                                screen.blit(text3, text_rect3)
                        if colour2 == RED:
                                text3 = fontTitle2.render("BLUE/LEFT SIDE WINS", True, BLUE)
                                text_rect3 = text3.get_rect(center=(xCenterTitle - 150, yCenterTitle))
                                screen.blit(text3, text_rect3)

                
                screen.fill(WHITE)          
                text = fontTitle.render("Thanks For Playing!", True, GREEN)
                text_rect = text.get_rect(center=(xCenter, yCenter))
                screen.blit(text, text_rect)

                text2 = fontSubTitle.render("Press Q to quit or ESCAPE to go back.", True, BLUE)
                text_rect2 = text2.get_rect(center=(xCenterSubtitle, yCenterSubtitle))
                screen.blit(text2, text_rect2)

            
                pygame.display.update()

        

pygame.quit()
sys.exit()



