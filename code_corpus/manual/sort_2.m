function dataOut = insertionSort(data)
  len = size(data,2);
  for j = 2:len;
    key = data(j);
    i = j-1;
    while(i>0 && data(i)>key)
      data(i+1) = data(i);
      i = i-1;
      data(i+1)=key;
    end
  end
dataOut = data;
