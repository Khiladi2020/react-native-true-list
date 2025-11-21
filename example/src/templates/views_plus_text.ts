const NAMES_POOL = ['Ravi', 'Sai', 'Rajesh', 'Raj', 'Rajeshwari', 'Bhavya'];
const DATA = new Array(1000).fill(0).map((_, index) => ({
  name: NAMES_POOL[index % NAMES_POOL.length],
  age: index,
}));

const BASIC_TEMPLATE1 = {
  data: DATA,
  template: {
    // This is the "root" view of your row (the LinearLayout)
    type: 'View',
    style: {
      padding: 56,
      backgroundColor: '#0055ff',
      marginVertical: 20,
      marginHorizontal: 10,
      justifyContent: 'center',
      layoutWidth: 'matchParent',
      // orientation: 'HORIZONTAL',
      orientation: 'VERTICAL',
    },
    children: [
      {
        type: 'View',
        style: {
          backgroundColor: '#000000', // Use hex strings
          orientation: 'HORIZONTAL',
          height: 300,
          width: 300,
          layoutWidth: 'matchParent',
          marginHorizontal: 12,
          // orientation: 'VERTICAL',
        },
        children: [
          {
            id: 'text1',
            type: 'Text',
            style: {
              backgroundColor: '#ff0000', // Use hex strings
              orientation: 'HORIZONTAL',
              height: 300,
              width: 300,
              marginLeft: 12,
              // orientation: 'VERTICAL',
            },
          },
        ],
      },
      {
        type: 'View',
        style: {
          // backgroundColor: '#00ff00', // Use hex strings
          orientation: 'HORIZONTAL',
          height: 300,
          // width: 300,
          marginHorizontal: 12,
          marginTop: 24,
          // orientation: 'VERTICAL',
        },
        children: [
          {
            type: 'View',
            style: {
              backgroundColor: '#ff55ff', // Use hex strings
              orientation: 'HORIZONTAL',
              height: 300,
              width: 300,
              // orientation: 'VERTICAL',
            },
          },
          {
            type: 'View',
            style: {
              backgroundColor: '#D2DCB6', // Use hex strings
              orientation: 'HORIZONTAL',
              marginHorizontal: 24,
              height: 300,
              width: 400,
              // orientation: 'VERTICAL',
            },
          },
        ],
      },
    ],
  },
  dataFitter: {
    text1: {
      key: 'name',
      type: 'string',
    },
  },
};

export default BASIC_TEMPLATE1;
