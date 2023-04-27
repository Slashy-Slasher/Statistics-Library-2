start = -25;
ending = 25;
Xarr = [];
Yarr = [];
x = 100;
windowSize = 3;
grit = 2;


%https://docs.octave.org/v6.3.0/Statistics-on-Sliding-Windows-of-Data.html#XREFmovmean
%https://www.mathworks.com/matlabcentral/answers/23591-how-do-i-combine-multiple-plots-in-one-graph
%https://www.mathworks.com/matlabcentral/answers/292226-how-to-plot-multiple-lines-in-a-graph
%https://octave.sourceforge.io/octave/function/csvread.html
%https://docs.octave.org/v6.3.0/Statistics-on-Sliding-Windows-of-Data.html



for i = start:ending
    Xarr(end+1) = i;
    Yarr(end+1) = i^2+2*i+1;
end
csvwrite('Unsalted Arr.csv', [Xarr;Yarr])
plot(Xarr, Yarr)

Yarr = [];
unsalted = dlmread ('Unsalted Arr.csv', ',');
temp = unsalted(2,:);

for i = 1:abs(start)+abs(ending)+1
    Yarr(end+1) = temp(i)+(randi([-x x]));
end

csvwrite('Salted Arr.csv', [Xarr;Yarr])
Yarr = [];
salted = dlmread ('Salted Arr.csv', ',');
temp = salted(2,:);

for i = 0:abs(grit)
  Yarr = movmean(temp, windowSize);
end

csvwrite('Smoothed Arr.csv', [Xarr;Yarr])

smoothed = dlmread ('Smoothed Arr.csv', ',');

plot(smoothed(1, :), smoothed(2, :))
hold on
plot(unsalted(1, :), unsalted(2, :))
hold on
plot(salted(1,: ), salted(2,:))
hold off

title("Combined Graph: Red is proper, Yellow is salted, Blue is smoothed");
%text ("Window Size: ", windowSize)
%text ("Salting ecshlons: ", x)
%text ("Grit", grit)

