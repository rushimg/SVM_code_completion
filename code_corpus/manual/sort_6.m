function dataOut = shellSort(data)
lenD = size(data,2);
inc = cast(lenD/2,'int16');
while(inc>0)
  for i=inc:lenD
    tmp = data(i);
    j = i;
    while(j>inc && data(j-inc)>tmp)
      data(j) = data(j-inc);
      j = j-inc;
    end
    data(j) = tmp;
  end
  inc = cast(floor(double(inc) /2),'int16');
end
dataOut = data;
