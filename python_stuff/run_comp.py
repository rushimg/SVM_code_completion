import subprocess
import os, sys

def run_preproc(in_dir):
	print "Running SVM on " + in_dir
	
	clean_cmd = 'bash ../train_test/clean_tests.sh ' + in_dir
	os.system(clean_cmd)
	
	train_cmd = 'python code_2_vecs_comp.py ' + in_dir
	os.system(train_cmd)
	
	test_cmd = 'python tcode_2_vecs_comp.py ' + in_dir
	os.system(test_cmd)
	
	learn_cmd = '../svm_light/svm_learn ' + in_dir+'train.dat ' + in_dir+'model'	
	os.system(learn_cmd)
	
	classify_cmd = '../svm_light/svm_classify ' + in_dir+'test.dat ' + in_dir+'model ' + in_dir+'predictions '
        os.system(classify_cmd)

 	map_cmd = 'bash ../train_test/map_tests_pred.sh ' + in_dir
	os.system(map_cmd)
	
if __name__=='__main__':
        run_preproc(sys.argv[1])
