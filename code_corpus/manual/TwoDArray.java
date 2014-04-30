

public class FilterExample
{
	public Color[][] smooth(Color[][] image, int neighberhoodSize)
	{
		assert image != null && image.length > 1 && image[0].length > 1
				&& ( neighberhoodSize > 0 ) && rectangularMatrix( image )
				: "Violation of precondition: smooth";

		Color[][] result = new Color[image.length][image[0].length];

		for(int row = 0; row < image.length; row++)
		{	for(int col = 0; col < image[0].length; col++)
			{	result[row][col] = aveOfNeighbors(image, row, col, neighberhoodSize);
			}
		}

		return result;
	}


	private Color aveOfNeighbors(Color[][] image, int row, int col, int neighberhoodSize)
	{	int numNeighbors = 0;
		int red = 0;
		int green = 0;
		int blue = 0;

		for(int r = row - neighberhoodSize; r <= row + neighberhoodSize; r++)
		{	for(int c = col - neighberhoodSize; c <= col + neighberhoodSize; c++)
			{	if( inBounds( image, r, c ) )
				{	numNeighbors++;
					red += image[r][c].getRed();
					green += image[r][c].getGreen();
					blue += image[r][c].getBlue();
				}
			}
		}

		assert numNeighbors > 0;
		return new Color( red / numNeighbors, green / numNeighbors, blue / numNeighbors );
	}

	private boolean inBounds(Color[][] image, int row, int col)
	{	return (row >= 0) && (row <= image.length) && (col >= 0)
				&& (col < image[0].length);
	}

	private boolean rectangularMatrix( Color[][] mat )
	{	boolean isRectangular = true;
		int row = 1;
		final int COLUMNS = mat[0].length;

		while( isRectangular && row < mat.length )
		{	isRectangular = ( mat[row].length == COLUMNS );
			row++;
		}

		return isRectangular;
	}
}
