function dataOut = mergeSort(data)
lenD = size(data,2);
if(lenD<2)
  dataOut = data;
else
  middle = cast(floor(lenD/2),'uint16');
  L = data(1:middle);
  R = data(middle+1:end);
  L = mergeSort(L);
  R = mergeSort(R);
  dataOut = merge(L, R);
end

