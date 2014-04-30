function dataOut = bubbleSort(data)
lenD = size(data,2);
for i = 1:lenD
  for j = (lenD):-1:(i+1)
    if(data(j)<data(j-1))
      tmp = data(j);
      data(j)=data(j-1);
      data(j-1)=tmp;
    end
  end
end
dataOut = data;
