function dataOut = selectionSort(data)
    lenD = size(data,2);
    for i=1:lenD
        j = i;
        for k = i:lenD
            if(data(j)>data(k))
                j = k;
            end
        end
        tmp = data(i);
        data(i) = data(j);
        data(j) = tmp;
     end
dataOut = data;
